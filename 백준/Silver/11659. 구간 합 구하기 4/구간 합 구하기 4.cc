#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);
	
	int n, m;
	int num[100002];
	int sum[100002];
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> num[i];
		if (i == 0) {
			sum[i] = num[i];
		}
		else {
			sum[i] = sum[i - 1] + num[i];
		}
	}

	int a, b;

	while (m--) {
		cin >> a >> b;
		if (a - 2 >= 0) {
			cout << sum[b - 1] - sum[a - 2] << '\n';
		}
		else {
			cout << sum[b - 1] << '\n';
		}
	}

	return 0;
}