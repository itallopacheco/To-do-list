#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

// Função que faz a mescla e conta inversões entre os índices l e r.
ll merge_count(vector<int>& arr, int l, int r, vector<int>& tmp) {
    if(l >= r) return 0;
    int mid = (l + r) / 2;
    ll inv = merge_count(arr, l, mid, tmp) + merge_count(arr, mid + 1, r, tmp);
    int i = l, j = mid + 1, k = l;
    while(i <= mid && j <= r) {
        if(arr[i] <= arr[j]) {
            tmp[k++] = arr[i++];
        } else {
            tmp[k++] = arr[j++];
            inv += mid - i + 1; // Todas as posições de i até mid formam inversão com arr[j-1].
        }
    }
    while(i <= mid) tmp[k++] = arr[i++];
    while(j <= r) tmp[k++] = arr[j++];
    for(int p = l; p <= r; p++) {
        arr[p] = tmp[p];
    }
    return inv;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    while (true) {
        int n;
        cin >> n;
        if(n == 0) break;
        
        vector<int> permutation(n);
        for (int i = 0; i < n; i++) {
            cin >> permutation[i];
        }
        
        vector<int> tmp(n);
        ll inversions = merge_count(permutation, 0, n - 1, tmp);
        // Marcelo vence se o número de inversões for ímpar; caso contrário, Carlos vence.
        cout << (inversions % 2 ? "Marcelo" : "Carlos") << "\n";
    }
    return 0;
}
