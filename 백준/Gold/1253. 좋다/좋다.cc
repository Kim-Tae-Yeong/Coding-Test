#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int N;
	cin >> N;

	int* a = new int[N];
	for (int i = 0; i < N; i++) {
		cin >> a[i];
	}
	sort(a, a + N);
	
	int count = 0;

	for (int i = 0; i < N; i++) {
		int start = 0;
		int end = N - 1;
		while (start < end) {
			int sum = a[start] + a[end];
			if (sum == a[i]) {
				if (start != i and end != i) {
					count += 1;
					break;
				}
				else if (start == i) {
					start += 1;
				}
				else if (end == i) {
					end -= 1;
				}
			}
			else if (sum > a[i]) {
				end -= 1;
			}
			else {
				start += 1;
			}
		}
	}

	cout << count << '\n';

	return 0;
}