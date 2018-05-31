package algorithms;

public class SearchUtils {

    public static int binarySearch(int[] arr, int item){
        int low = 0;
        int hign = arr.length;
        while(low <= hign){
            int mid = low + (hign - low ) / 2;
            if(arr[mid] == item){
                return mid;
            }
            if(arr[mid] < item){
                low = mid + 1 ;
            }else{
                hign = mid - 1;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,200,200,230,443};
        assert binarySearch(arr, 200) == 5;
        assert binarySearch(arr, 443) == 8;
        assert binarySearch(arr, 2) == 1;
        assert binarySearch(arr, 22) == -1;
    }
}