package simple;




/**
 * 线段树
 */
public class SegmentTree<E> {

    public int[] data;

    public int[] tree;

    public Merge merge;

    public SegmentTree() {
    }

    public SegmentTree(int[] arr) {
        this.merge=merge;
        data=arr;
        tree=  new int[data.length*4];
        initialize(0,0,data.length-1);
    }

    private void initialize(int treeIndex,int l,int r){
        if (l==r){
            tree[treeIndex]=data[l];
            return;
        }

        int mid=l+(r-l)/2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);
        initialize(leftChild,l,mid);
        initialize(rightChild,mid+1,r);
        tree[treeIndex]=merge.merge(tree[leftChild],tree[rightChild]);
    }

    public int query(int l,int r){
        if (l<0||l>=data.length||r<0||r>=data.length||l>r)
            throw new RuntimeException("传进来的索引不对");
        return query(0,0,data.length-1,l,r);
    }

    private int query(int treeIndex,int l,int r,int queryL,int queryR){
        if (l==queryL&&r==queryR){
            return tree[treeIndex];
        }
        int mid=l+(r-l)/2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);
        if (queryL>mid)
            return query(rightChild,mid+1,r,queryL,queryR);
        if (queryR<=mid)
            return query(leftChild,l,mid,queryL,queryR);
        else {
            int dataR = query(rightChild, mid + 1, r, mid+1, queryR);
            int dataL = query(leftChild, l, mid, queryL, mid);
            return merge.merge(dataL,dataR);
        }
    }

    public void update(int index,int e){
        if (index<0||index>data.length-1)
            throw new  RuntimeException("传进来的索引不对");
        data[index]=e;
        update(0,0,data.length-1,index,e);

    }

    private void update(int treeIndex,int l,int r,int index, int e){
        if (l==r&&l==index){
            tree[treeIndex]=e;
            return;
        }
        int mid=l+(r-l)/2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);
        if (index>mid){
            update(rightChild,mid+1,r,index,e);
        }else {
            update(leftChild,l,mid,index,e);
        }
        tree[treeIndex]=merge.merge(tree[leftChild],tree[rightChild]);
    }

    private int leftChild(int index){
        return index*2+1;
    }

    private int rightChild(int index){
        return index*2+2;
    }

    @Override
    public String toString(){
        StringBuilder builder=new StringBuilder();
        toString(0,0,data.length-1,builder);
        return builder.toString();
    }

    private void toString(int index,int l,int r,StringBuilder builder){
            if (l==r){
                builder.append(tree[index]).append(",");
                return;
            }
        int mid=l+(r-l)/2;
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);
        toString(leftChild,l,mid,builder);
        toString(rightChild,mid+1,r,builder);
    }
}
