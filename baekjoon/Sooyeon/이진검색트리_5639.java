import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 이진검색트리_5639 {
    static StringBuilder sb = new StringBuilder();
    static class Node {
        int data;
        Node child1, child2;
        
        Node(int data) {
            this.data =data;
        }

        void addChild(int getData) {
            if(getData < data) {
                if(this.child1 != null) {
                    this.child1.addChild(getData);
                } else {
                    this.child1 = new Node(getData);
                }
            } else {
                if(this.child2 != null) {
                    this.child2.addChild(getData);
                } else {
                    this.child2 = new Node(getData);
                }
            }
        }
    }
    static Node rootNode;
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            rootNode = new Node(Integer.parseInt(bf.readLine()));
        } catch (Exception e) {
        }
        while(true) {
            try {
                rootNode.addChild(Integer.parseInt(bf.readLine()));
            } catch (Exception e) {
                postorder(rootNode);
                System.out.print(sb);
                // bw.flush();
                return;
            }
        }
    }
    static void postorder(Node node) {
        if(node.child1 != null) {
            postorder(node.child1);
        }
        if(node.child2 != null) {
            postorder(node.child2);
        }
        sb.append(node.data+"\n");
    }
}
