package Questions_july_2025;

public class _1290 {
    public static void main(String[] args) {
        ListNode1290 head = new ListNode1290(1,
                new ListNode1290(0,
                        new ListNode1290(1)
                )
        );

        int result = new Solution1290().getDecimalValue(head);
        System.out.println("Decimal value: " + result); // should print 5
    }

    static class ListNode1290 {
        int val;
        ListNode1290 next;
        ListNode1290(int val) { this.val = val; }
        ListNode1290(int val, ListNode1290 next) { this.val = val; this.next = next; }
    }

    static class Solution1290 {
        public int getDecimalValue(ListNode1290 head) {
            int num = 0;
            while (head != null) {
                num = (num << 1) | head.val;
                head = head.next;
            }
            return num;
        }
    }
}

