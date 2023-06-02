public class LinkedList {
    public static class SingleLinkedList {
        class sNode {
            int value;
            sNode next;

            sNode(int value) {
                this.value = value;
                next = null;
            }
        }

        sNode createSingleList(int[] arr) {
            sNode fictitiousHead = new sNode(0);
            sNode current = fictitiousHead;
            for (int val : arr) {
                current.next = new sNode(val);
                current = current.next;
            }
            return fictitiousHead.next;
        }

        void printList(sNode node) {
            while (node != null) {
                System.out.print(node.value + " ");
                node = node.next;
            }
            System.out.println();
        }

        sNode reverseSingleList(sNode head) {
            sNode previous = null;
            sNode current = head;
            while (current != null) {
                sNode nextTemp = current.next;
                current.next = previous;
                previous = current;
                current = nextTemp;
            }
            return previous;
        }
    }

    public static class DoubleLinkedList {
        class dNode {
            int value;
            dNode next;
            dNode previous;

            dNode(int value) {
                this.value = value;
                next = null;
                previous = null;
            }
        }

        dNode createDoubleList(int[] arr) {
            dNode fictitiousHead = new dNode(0);
            dNode previous = fictitiousHead;
            for (int val : arr) {
                dNode newNode = new dNode(val);
                newNode.previous = previous;
                previous.next = newNode;
                previous = newNode;
            }
            fictitiousHead.next.previous = null;
            return fictitiousHead.next;
        }

        void printList(dNode node) {
            while (node != null) {
                System.out.print(node.value + " ");
                node = node.next;
            }
            System.out.println();
        }

        dNode reverseDoubleList(dNode head) {
            dNode current = head;
            dNode temp = null;
            while (current != null) {
                temp = current.previous;
                current.previous = current.next;
                current.next = temp;
                current = current.previous;
            }
            if (temp != null) {
                head = temp.previous;
            }
            return head;
        }
    }


    public static void main(String[] args) {
        System.out.println("Single linked list: ");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList.sNode headSingle = singleLinkedList.createSingleList(new int[]{1, 2, 3, 4, 5});
        singleLinkedList.printList(headSingle);
        headSingle = singleLinkedList.reverseSingleList(headSingle);
        singleLinkedList.printList(headSingle);


        System.out.println("Double linked list: ");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        DoubleLinkedList.dNode headDouble = doubleLinkedList.createDoubleList(new int[]{1, 2, 3, 4, 5});
        doubleLinkedList.printList(headDouble);
        headDouble = doubleLinkedList.reverseDoubleList(headDouble);
        doubleLinkedList.printList(headDouble);
    }
}
