#include <iostream>
#include <vector>
using namespace std;

struct Node {
    int cnt[3];
};

int N, M;
vector<Node> segtree;
vector<int> lazy;

void build(int idx, int l, int r) {
    if(l == r) {
        // initially all players choose Man (index 0)
        segtree[idx].cnt[0] = 1;
        segtree[idx].cnt[1] = 0;
        segtree[idx].cnt[2] = 0;
        return;
    }
    int mid = (l + r) / 2;
    build(idx*2, l, mid);
    build(idx*2+1, mid+1, r);
    for(int i=0; i<3; i++){
        segtree[idx].cnt[i] = segtree[idx*2].cnt[i] + segtree[idx*2+1].cnt[i];
    }
}

void applyRotation(Node &node, int shift) {
    // rotate the counts by "shift"
    int temp[3];
    for (int i = 0; i < 3; i++)
        temp[i] = node.cnt[i];
    for (int i = 0; i < 3; i++)
        node.cnt[i] = temp[(i - shift + 3) % 3];
}

void push_down(int idx, int l, int r) {
    if(lazy[idx] != 0) {
        int mid = (l + r) / 2;
        int left = idx * 2, right = idx * 2 + 1;
        // apply the lazy value to children
        lazy[left] = (lazy[left] + lazy[idx]) % 3;
        lazy[right] = (lazy[right] + lazy[idx]) % 3;
        // update children nodes
        applyRotation(segtree[left], lazy[idx]);
        applyRotation(segtree[right], lazy[idx]);
        lazy[idx] = 0;
    }
}

void update(int idx, int l, int r, int ql, int qr) {
    if(ql > r || qr < l)
        return;
    if(ql <= l && r <= qr) {
        // update current node: apply a cyclic shift by 1
        lazy[idx] = (lazy[idx] + 1) % 3;
        applyRotation(segtree[idx], 1);
        return;
    }
    push_down(idx, l, r);
    int mid = (l + r) / 2;
    update(idx*2, l, mid, ql, qr);
    update(idx*2+1, mid+1, r, ql, qr);
    for(int i = 0; i < 3; i++){
        segtree[idx].cnt[i] = segtree[idx*2].cnt[i] + segtree[idx*2+1].cnt[i];
    }
}

Node query(int idx, int l, int r, int ql, int qr) {
    if(ql > r || qr < l) {
        Node empty = { {0, 0, 0} };
        return empty;
    }
    if(ql <= l && r <= qr)
        return segtree[idx];
    push_down(idx, l, r);
    int mid = (l + r) / 2;
    Node left = query(idx*2, l, mid, ql, qr);
    Node right = query(idx*2+1, mid+1, r, ql, qr);
    Node res;
    for(int i = 0; i < 3; i++){
        res.cnt[i] = left.cnt[i] + right.cnt[i];
    }
    return res;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    while(cin >> N >> M) {
        // allocate segment tree arrays; 4*N is safe size.
        segtree.assign(4 * N, Node());
        lazy.assign(4 * N, 0);
        build(1, 1, N);
        while(M--){
            char type;
            int A, B;
            cin >> type >> A >> B;
            if(type == 'M'){
                // update range [A, B]: cyclic shift by one.
                update(1, 1, N, A, B);
            } else { // type == 'C'
                Node ans = query(1, 1, N, A, B);
                cout << ans.cnt[0] << " " << ans.cnt[1] << " " << ans.cnt[2] << "\n";
            }
        }
        cout << "\n";
    }
    return 0;
}
