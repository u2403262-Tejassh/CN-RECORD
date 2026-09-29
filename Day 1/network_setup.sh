#!/usr/bin/env bash
# Configure an IPv4 interface, set its default route, verify connectivity,
# and save the routing table. Run as root for changes; --dry-run is safe.
set -euo pipefail

usage() {
    echo "Usage: $0 [--dry-run] INTERFACE ADDRESS/CIDR GATEWAY TEST_HOST [ROUTE_FILE]" >&2
    exit 2
}

dry_run=0
if [[ ${1:-} == --dry-run ]]; then
    dry_run=1
    shift
fi
[[ $# -ge 4 && $# -le 5 ]] || usage
iface=$1
address=$2
gateway=$3
test_host=$4
route_file=${5:-routing_info.txt}

# Avoid passing arbitrary interface names or addresses to privileged commands.
[[ $iface =~ ^[a-zA-Z0-9_.:-]+$ ]] || usage
[[ $address =~ ^[0-9.]+/[0-9]+$ ]] || usage
[[ $gateway =~ ^[0-9.]+$ ]] || usage
[[ $test_host =~ ^[a-zA-Z0-9.:-]+$ ]] || usage
command -v ip >/dev/null && command -v ping >/dev/null || {
    echo "Install iproute2 and iputils-ping first." >&2
    exit 1
}
if (( ! dry_run && EUID != 0 )); then
    echo "Root privileges are required; rerun with sudo or use --dry-run." >&2
    exit 1
fi

run() {
    printf '+ '
    printf '%q ' "$@"
    printf '\n'
    if (( ! dry_run )); then "$@"; fi
}

run ip addr replace "$address" dev "$iface"
run ip link set "$iface" up
run ip route replace default via "$gateway" dev "$iface"
if (( dry_run )); then
    echo "DRY RUN: configuration and ping were not executed."
    echo "DRY RUN: would ping $test_host and save 'ip route show' to $route_file."
    exit 0
fi

# Save the route even if the connectivity test fails.
status=0
ping -c 2 -W 2 "$test_host" || status=$?
ip route show | tee "$route_file"
echo "Routing information saved to $route_file"
exit "$status"
