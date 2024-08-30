public class Driver
{
    public static void main(String[] args)
    {
        genericLL<Integer> list = new genericLL<>();
        list.insert(1);
        list.goToNext();
        list.insert(2);
        list.goToNext();
        list.insert(3);
        list.goToNext();
        list.insert(4);
        list.goToNext();
        list.insert(5);
        list.goToNext();
        list.insert(6);
        list.goToNext();
      
        list.print();      

        list.goToNodeWithData(2);
        list.deleteCurr();
        list.goToNodeWithData(5);
        list.deleteCurr();

        list.print();
    }
}
