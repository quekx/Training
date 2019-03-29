---
title: 学习笔记(2):红黑树删除
date: 2017-04-04 11:29:39
categories: 
- algorithm
tags: 
- Java
- Red Black Tree
toc: true 
---

### 普通二叉搜索树删除
普通二叉树删除节点z有三种情况：

* z的左子节点为空，即用z的右子节点（包括空的情况）取代z
* z的右子节点为空，即用z的左子节点取代z
* z的左子节点和右子节点均不为空，可在其左子树找最大节点或者在其右子树找最小节点y，将y的值赋予z，然后将y删除

# (todo)以下删除分析存在错误，我会抽时间马上修改。

### 红黑树删除
红黑树删除节点的操作与普通二叉搜索树的删除节点一致，不同之处在于删除时可能会破坏红黑树的性质，需要后续的调整来恢复其红黑性质。

红黑树将节点删除后，最终的结果是对删除节点后的替代节点x所在的子树产生影响。

可以思考下红黑树的哪些性质可能被破坏？

* 如果删除的节点是一个红色节点，显然红黑树的性质依然成立，无需调整。
* 如果删除的节点是一个黑色节点，那么替代节点x所在的子树黑高将缺少1，红黑树的性质五被破坏。

调整的方法是从替代节点x开始，若x是一个红节点，直接设为黑色即可。若x是一个黑节点，则需要循环向上调整，直到找到一个红节点，将其颜色改为黑色结束，或者到达根节点结束。

以当前节点x是其父节点xp的左子节点为例（右子节点为对称情况），删除需要调整的情况有三种。

* 情况1： 当前节点x为黑色，父节点xp为红色（图中Ax表示A子树的高黑为x）：

{% asset_img delete1.png 调整操作将xr设置为红色，然后将xp左旋，将xr设置为新的x。 %}

对于此子树，调整前左侧黑高为x+1，右侧黑高为x+2，通过调整可找到新的红节点x，设置为黑色后恢复完毕；跳出循环后将x设为黑色后，可使左右两侧黑高均恢复为x+2，调整完毕。

* 情况2： 当前节点x为黑色，父节点xp为黑色，父节点的右子节点xr为红色：

{% asset_img delete3.png 调整操作将d设置为红色，然后将xp左旋，将xr设置为新的x。 %}

对于此子树，调整前左侧黑高为x+2，右侧黑高为x+3，通过调整可找到新的红节点x，当x设置为黑色后恢复完毕；因此将其设为红色（不改变其红色）跳出循环，最后将x设为黑色后，可使左右两侧黑高均恢复为x+3，调整完毕。

* 情况3： 当前节点x为黑色，父节点xp为黑色，父节点的右子节点xr为黑色：

{% asset_img delete2.png 调整操作将xp设置为红色，然后将xp左旋，将xr设置为新的x。 %}

对于此子树，调整前左侧黑高为x+2，右侧黑高为x+3，通过调整无法使得左右两侧的黑高都恢复为x+3，整棵子树的黑高为x+2依旧缺少1，需要继续向上调整，因此将xr设置为新的x，但其不能设为红色，因为设为红色会跳出循环。

情况1和情况2都通过调整恢复了红黑性质（黑高相等），因此最后x的颜色为红色使其跳出循环；情况3只是平衡了左右侧的高黑，依旧需要向上调整来恢复黑高，因此最后x的颜色为黑色。

因此，x满足一下条件可终止循环（删除调整完毕）：

* x为红节点（情况1、情况2）
* x为根节点（情况3回溯到了根节点，整棵树黑高减1）

### 实现
删除调整的代码如下：

```
private void deleteFixUp(RBTreeNode x, RBTreeNode xp) {
    if (root == null || xp == null) return;

    // 删除叶子时x为空
    while ((x == null || x.color == RBTreeNode.Color.BLACK) && x != root) {
        if (x == xp.left) {
            RBTreeNode xr = xp.right;
            if (xp.color == RBTreeNode.Color.RED) {
                // 情况1
                xr.color = RBTreeNode.Color.RED;
                x = rotateLeft(xp);
            } else if (xr.color == RBTreeNode.Color.RED) {
                // 情况2
                RBTreeNode d = xr.left;
                d.color = RBTreeNode.Color.RED;
                x = rotateLeft(xp);
            } else {
                // 情况3
                xp.color = RBTreeNode.Color.RED;
                x = rotateLeft(xp);
            }
            if (xp == root) root = xr; // 若旋转节点为根节点，则设置新的根节点
        } else {
            RBTreeNode xl = xp.left;
            if (xp.color == RBTreeNode.Color.RED) {
                // 情况1 
                xl.color = RBTreeNode.Color.RED;
                x = rotateRight(xp);
            } else if (xl.color == RBTreeNode.Color.RED) {
                // 情况2
                RBTreeNode d = xl.right;
                d.color = RBTreeNode.Color.RED;
                x = rotateRight(xp);
            } else {
                // 情况3
                xp.color = RBTreeNode.Color.RED;
                x = rotateRight(xp);
            }
            if (xp == root) root = xl; // 若旋转节点为根节点，则设置新的根节点
        }
        xp = x.parent;
    }
    // 最后两种情况
    // 1.找到红色的x
    // 2.x回溯到了根节点
    x.color = RBTreeNode.Color.BLACK;
}

```
