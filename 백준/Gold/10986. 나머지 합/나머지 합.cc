#include <iostream>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);
	
	int N, M, x;
	long long cnt[1001];
	long long sum = 0;
	long long ans = 0;

	for (int i = 0; i < 1001; i++) {
		cnt[i] = 0;
	}

	cin >> N >> M;
	
	for (int i = 0; i < N; i++) {
		cin >> x;
		sum += x;
		cnt[sum % M] += 1;
	}

	for (int i = 0; i <= 1000; i++) {
		ans += (cnt[i] * (cnt[i] - 1)) / 2;
	}
	cout << cnt[0] + ans << '\n';

	return 0;
}