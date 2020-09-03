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

# 普通二叉搜索树删除
普通二叉树删除节点z有三种情况：

* z的左子节点为空，即用z的右子节点（包括空的情况）取代z
* z的右子节点为空，即用z的左子节点取代z
* z的左子节点和右子节点均不为空，可在其左子树找最大节点或者在其右子树找最小节点y，将y的值赋予z，然后将y删除


# 红黑树删除
红黑树删除节点的操作与普通二叉搜索树的删除节点一致，不同之处在于删除时可能会破坏红黑树的性质，需要后续的调整来恢复其红黑性质。

## 删除操作
红黑树将节点删除后，首先是正常的二叉树删除操作，操作如下
> * 第一种情况，删除节点无左子树，用右子树上移替代当前节点
> * 第二种情况，删除节点无右子树，用左子树上移替代当前节点
> * 第三种情况，删除节点存在左右子树，找到当前节点的替代节点（左子树的最大节点或者右子树的最小节点），用替代节点值赋予删除节点（该节点不作物理删除），然后删除替代节点（物理删除）。ps：为什么这么操作？因为这种方式找到的替代节点，左右子树必有一个为空。物理删除时，直接用子树移植替换就可以了

{% asset_img deleteNormal.png 二叉树删除 %}

删除最终的结果是对由于物理删除，移植替换的节点x所在的子树产生影响。

## 性质调整

可以思考下红黑树的哪些性质可能被破坏？

> * 如果删除的节点是一个红色节点，显然红黑树的性质依然成立，无需调整。
> * 如果删除的节点是一个黑色节点，那么移植节点x所在的子树黑高将缺少1，红黑树的性质五被破坏。

**性质五被破坏，x子树高比兄弟子树高缺1，如下图** 

{% asset_img sample.jpg 黑高性质被破坏 %}

**调整的方法是从移植节点x开始，通过循环向上调整（联合父节点和兄弟子树进行旋转、修改颜色），使左右子树高相等，左右子树高相等后有三种情况**

* 情况1：父子树整体黑高不变，此时调整完成
* 情况2：父子树整体黑高-1，但是已经是根节点，调整完成（整颗树黑高-1）
* 情况3：父子树整体黑高-1，还没到根节点，将x设置为父子树跟节点，继续向上调整，直到情况1或者情况2结束

由此可以将调整循环结束条件设定为

1. 当前调整节点x为红色，针对以下情况
	1. 删除节点为红色，无需调整
	2. 情况1，调整完成，将x设置为红色退出循环
2. 当前调整节点x为根节点
	1. 情况2，已经到达根节点，调整完成


下面开始分情况讨论，以当前调整节点为父节点的作子节点为例（右子节点为对称情况）

图例中调整节点为X，其父节点为XP，其右边兄弟节点为XR；蓝色表示即可以是红节点也可以是黑节点


### 情况1
调整节点为红

{% asset_img s1.jpg 情况一 %}

* 将x置黑，调整完成！

### 情况2
调整节点X为黑，父节点XP为红，兄弟节点XR为黑，兄弟节点的左子节点A为黑，右子节点B无所谓

{% asset_img s2.1.jpg 情况二 %}

* 将XP左旋，调整完成！

### 情况3
调整节点X为黑，父节点XP为红，兄弟节点XR为黑，兄弟节点的左子节点A为红，右子节点B为红

{% asset_img s3.1.jpg 情况三 %}

* 将XP左旋
* 将XP置黑
* 将XR置红
* 将B置黑，调整完成！

### 情况4
调整节点X为黑，父节点XP为红，兄弟节点XR为黑，兄弟节点的左子节点A为红，右子节点B为黑

{% asset_img s4.jpg 情况四 %}

* 将XR右旋
* 将A置黑
* 将XR置红，此时切换至情况1，继续循环调整

### 情况5
调整节点X为黑，父节点XP为黑，兄弟节点XR为黑，兄弟节点的左子节点A为黑，右子节点B无所谓

{% asset_img s5.jpg 情况五 %}

* 将XP左旋
* 将XP置红，此时左右子树高相等，整体树高-1，将根节点置为X，继续循环调整

### 情况6
调整节点X为黑，父节点XP为黑，兄弟节点XR为黑，兄弟节点的左子节点A为红，右子节点B为红

{% asset_img s6.jpg 情况六 %}

* 将XP左旋
* 将B置黑，调整完成！

### 情况7
调整节点X为黑，父节点XP为黑，兄弟节点XR为黑，兄弟节点的左子节点A为红，右子节点B为黑

{% asset_img s7.jpg 情况七 %}

* 将XR右旋
* 将XR置红
* 将A置黑，此时切换至情况5，继续循环调整

### 情况8
调整节点X为黑，父节点XP为黑，兄弟节点XR为红

{% asset_img s8.jpg 情况八 %}


### 所有调整情况
{% asset_img rbt2.0.1.png 所有调整情况 %}


## 实现
删除调整的代码如下：

```
private void deleteFixUp2(RBTreeNode x, RBTreeNode xp) {
    if (root == null || xp == null) return;

    // 退出调整循环条件：当前x为红或者x调整至根节点
    // 情况1：不进入循环
    while ((x == null || x.color == RBTreeNode.Color.BLACK) && x != root) {
        // x为作子节点
        if (x == xp.left) {
            RBTreeNode xr = xp.right;
            RBTreeNode a = xr.left;
            RBTreeNode b = xr.right;
            if (xr.color == RBTreeNode.Color.BLACK) {
                // 兄弟节点为黑
                // 情况2、情况3、情况4、情况5、情况6、情况7
                if (a == null || a.color == RBTreeNode.Color.BLACK) {
                    // 情况2、情况5
                    // xp左旋
                    // 将x指向xr
                    // 将xr(x)颜色设置为xp颜色
                    // 情况2：xp为红，调整完成，x置为红退出循环
                    // 情况5：xp为黑，需要继续下一轮调整
                    rotateLeft(xp);
                    x = xr;
                    xr.color = xp.color;
                } else if (b != null && b.color == RBTreeNode.Color.RED) {
                    // 情况3，情况6
                    // xp左旋
                    // 将xr颜色置为xp颜色（对应情况3、情况6）
                    // xp颜色置黑
                    // 两种情况均调整完成，x置红，退出循环
                    rotateLeft(xp);
                    xr.color = xp.color;
                    xp.color = RBTreeNode.Color.BLACK;
                    x.color = RBTreeNode.Color.RED;
                } else if (b == null || b.color == RBTreeNode.Color.BLACK) {
                    // 情况4，情况7
                    // xr右旋
                    // a红置黑
                    // xr黑置红
                    // 调整至情况2、情况5，继续下一轮调整
                    rotateRight(xr);
                    a.color = RBTreeNode.Color.BLACK;
                    xr.color = RBTreeNode.Color.RED;
                }
            } else {
                // 兄弟节点为红，父节点必为黑
                // 情况8
                // 左旋xp
                // xp置红
                // xr置黑
                // 此时x的父节点xp变为红色，切换至情况2或情况3或情况4，继续下一轮调整
                rotateLeft(xp);
                xp.color = RBTreeNode.Color.RED;
                xr.color = RBTreeNode.Color.BLACK;
            }
        } else {
            // 对称情况
            RBTreeNode xl = xp.left;
            RBTreeNode a = xl.left;
            RBTreeNode b = xl.right;
            if (xl.color == RBTreeNode.Color.BLACK) {
                if (b == null || b.color == RBTreeNode.Color.BLACK) {
                    rotateRight(xp);
                    x = xl;
                    xl.color = xp.color;
                } else if (a != null && a.color == RBTreeNode.Color.RED) {
                    rotateRight(xp);
                    xl.color = xp.color;
                    xp.color = RBTreeNode.Color.BLACK;
                    x.color = RBTreeNode.Color.RED;
                } else if (a == null || a.color == RBTreeNode.Color.BLACK) {
                    rotateRight(xl);
                    b.color = RBTreeNode.Color.BLACK;
                    xl.color = RBTreeNode.Color.RED;
                }
            } else {
                rotateRight(xp);
                xp.color = RBTreeNode.Color.RED;
                xl.color = RBTreeNode.Color.BLACK;
            }
        }
        // 如果x指向新节点，更新xp
        xp = x != null ? x.parent : xp;
    }
    x.color = RBTreeNode.Color.BLACK;
}

```
