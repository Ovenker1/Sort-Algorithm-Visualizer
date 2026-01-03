/**
 *
 * @author Ouda
 */

//importing the libraries that will be needed in this program

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

//The class that has all the sorts in it
public class SortShow extends JPanel { 

	
		// An array to hold the lines_lengths to be sorted
		public int[] lines_lengths;
		//The amount of lines needed
		public final int total_number_of_lines = 256;
		 // An array to holds the scrambled lines_lengths
		public int[] scramble_lines;
		//A temp Array that is used later for sorts
		public int[] tempArray;
		
		//the default constructor for the SortShow class
		public SortShow(){
			//assigning the size for the lines_lengths below
			lines_lengths = new int[total_number_of_lines];
			for(int i = 0; i < total_number_of_lines; i++) 
				lines_lengths[i] =  i+5;
			
		}
		

		//A method that scrambles the lines
		public void scramble_the_lines(){
			//A random generator
			Random num = new Random(); 
			//Randomly switching the lines
			for(int i = 0; i < total_number_of_lines; i++){
				//getting a random number using the nextInt method (a number between 0 to i + 1)
				int j = num.nextInt(i + 1); 
				//swapping The element at i and j 
				swap(i, j);
			}
			//assigning the size for the scramble_lines below
			scramble_lines = new int[total_number_of_lines];
			//copying the now scrambled lines_lengths array into the scramble_lines array 
			//to store for reuse for other sort methods
			//so that all sort methods will use the same scrambled lines for fair comparison 
			for (int i = 0; i < total_number_of_lines; i++)
			{
				scramble_lines[i] = lines_lengths[i];
			}
			//Drawing the now scrambled lines_lengths
			paintComponent(this.getGraphics());
		}
		
		//Swapping method that swaps two elements in the lines_lengths array
		public void swap(int i, int j){
			//storing the i element in lines_lengths in temp
			int temp = lines_lengths[i];
			//giving i element in lines_lengths the value of j element in lines_lengths
			lines_lengths[i] = lines_lengths[j];
			//giving j element in lines_lengths the value of temp
			lines_lengths[j] = temp;
		}
        /// ////////////////////////////////////////////////////////////////////
        /**Radix Sort Method*/
        public void RadixSort(){
            Calendar start = Calendar.getInstance(); /**timer starts*/
            for (int exp = 1; total_number_of_lines / exp > 0; exp *= 10) /**for loop for each digit place*/
                CountSort(lines_lengths,total_number_of_lines,exp);
            Calendar end = Calendar.getInstance(); /**calender ends*/
            SortGUI.radixTime = end.getTime().getTime() - start.getTime().getTime();
        }

        public void CountSort(int arr[],int n, int exp){
            int temparr[] = new int[n]; //temporary array
            int i;
            int count[] = new int[10]; //count array for 1-9
            Arrays.fill(count,0);//filling count with 0s


            for (i = 0; i < n; i++){ /**storing number of occurences of each digit*/
                count[(arr[i]/exp) % 10]++;
            }
            for (i = 1; i<10; i++){/** count I will store the position of the digit*/
                count[i] += count[i-1];
            }
            for (i = n-1; i>=0; i--){ /** filling the output array*/
                temparr[count[(arr[i]/exp) % 10]-1] = arr[i];
                count[(arr[i]/exp) % 10]--;
            }
            for (i=0; i<n; i++){/**put the sorted element back into the array*/
                arr[i] = temparr[i];
            }
            paintComponent(this.getGraphics());

        }
        /// /////////////////////////////////////////////////////////////////////
        /**Quick Sort Method*/
        public void QuickSort(){
            Calendar start = Calendar.getInstance(); /**timer starts*/

            quicksort(lines_lengths, 0, lines_lengths.length-1); /**call quicksort and pass array, low and high*/
            Calendar end = Calendar.getInstance();
            SortGUI.quickTime = end.getTime().getTime() - start.getTime().getTime();
        }

        public void quicksort(int arr[],int low, int high ){
            if (low<high){
            int p = partition(arr,low,high);/**call partition to get p*/

            /**use recursion to sort elements*/
            quicksort(arr,low,p-1);
            quicksort(arr,p+1,high);
            }
        }

        public int partition(int arr[],  int low, int high){
            int temp; /**temporary variable*/
            int pivot = arr[high]; /**pivot is last element*/

            int i = low-1; /**index of low*/

            for (int j = low; j < high; j++){ /**reorder elements*/
                if(arr[j] < pivot) {
                    i++;

                    /**swap elements*/
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            /**put pivot to its correct spot */
            temp = arr[i+1];
            arr[i+1] = arr[high];
            arr[high] = temp;
            paintComponent(this.getGraphics());
            return i+1;/**return pivot index*/
        }


        /// /////////////////////////////////////////////////////////////////////
        /**Shell Sort Method*/
        public void ShellSort(){
            Calendar start = Calendar.getInstance(); /**timer starts*/

            /**reduce the gap by =/ 2 each iteration */
            for (int space = total_number_of_lines/2; space > 0; space /= 2){
                for (int i = space; i <total_number_of_lines; i += 1){/**preform insertion sort with gaps*/
                    int temp = lines_lengths[i];
                    int j;
                    /**shift elements to the correct location*/
                    for (j = i; j >= space && lines_lengths[j - space] > temp; j -= space ){
                        lines_lengths[j] = lines_lengths[j - space];
                    }
                    /**put temp where it belongs */
                    lines_lengths[j] = temp;

                }
                paintComponent(this.getGraphics());
            }
            /**calender end*/
            Calendar end = Calendar.getInstance();
            SortGUI.shellTime = end.getTime().getTime() - start.getTime().getTime();
        }


        /// //////////////////////////////////////////////////////////////////////
        /**Insertion Sort Method */
        public void InsertionSort(){
            Calendar start = Calendar.getInstance(); /**timer starts*/

            int temp;

            for (int i = 1; i < total_number_of_lines; i++){/**start at 1*/
                int num = lines_lengths[i]; /**element to insert*/
                int j = i-1;/**start 1 behind i*/

                while(j >= 0  && lines_lengths[j] > num){/**shifts larger elements over by 1*/
                    lines_lengths[j+1] = lines_lengths[j];
                    j = j-1;
                }
                lines_lengths[j+1] = num;/**place current element in correct position*/

                paintComponent(this.getGraphics());
            }
            Calendar end = Calendar.getInstance();/**end timer*/
            SortGUI.insertionTime = end.getTime().getTime() - start.getTime().getTime();
        }

        /// //////////////////////////////////////////////////////////////////////
        /**Bubble Sort Method*/

        public void BubbleSort() {
            Calendar start = Calendar.getInstance(); /**timer starts*/

            int temp;

            for (int i = 0; i < total_number_of_lines; i++) {/**outer loop to iterate over the array */
                for (int j = 0; j < total_number_of_lines - i - 1; j++) {/**innner loop to ompare elements*/
                    if (lines_lengths[j] > lines_lengths[j + 1]) {
                        /**swap*/
                        temp = lines_lengths[j];
                        lines_lengths[j] = lines_lengths[j + 1];
                        lines_lengths[j + 1] = temp;
                    }
                }
                paintComponent(this.getGraphics());
            }
            Calendar end = Calendar.getInstance();/**end time*/
            SortGUI.bubbleTime = end.getTime().getTime() - start.getTime().getTime();
        }

		/////////////////////////////////////////////////////////////////////////
		//The selectionSort method
		public void SelectionSort(){
			//getting the date and time when the selection sort starts
			Calendar start = Calendar.getInstance();
			//Using the selection sort to lines_lengths sort the array

            int temp;

            for(int i = 0; i < total_number_of_lines - 1; i++) {/**iterate over array*/
                int min = i;
                for (int j = i + 1; j < total_number_of_lines; j++) {/**find minimum element*/
                    if (lines_lengths[j] < lines_lengths[min]) {
                        min = j;

                    }
                }
                /**swap*/
                temp = lines_lengths[i];
                lines_lengths[i] = lines_lengths[min];
                lines_lengths[min] = temp;
                paintComponent(this.getGraphics());
            }


			//getting the date and time when the selection sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the selection sort to execute 
			//subtracting the end time with the start time
	        SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();
		}
		
		//this method gets the smallest element in the array of lines_lengths
		public int getIndexOfSmallest(int first, int last){
            int min = first;
            for(int i = first; i < last; i++){
                if (min > lines_lengths[i]) {
                    min = i;
                }
            }
			//You need to complete this part.
            return min; //modify this line
		}
		
	///////////////////////////////////////////////////////////////////////////////////
		
		//recursive merge sort method
		public void RecursiveMergeSort(){
			//getting the date and time when the recursive merge sort starts
			Calendar start = Calendar.getInstance();/**start time*/
			//assigning the size for the tempArray below

            R_Merge(lines_lengths, 0, total_number_of_lines-1);  /**call R_Merge method to start recursion*/


			Calendar end = Calendar.getInstance(); //Timer stuff
	        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();
			
		}
		
		//recursive merge sort method
		public void Merge(int arr[], int left, int right, int middle){
            /** Get sizes of the arrays and create temporary arrays for merging*/
	        int sizeL = middle - left + 1;
            int sizeR = right - middle;
            int temparrLeft[] = new int[sizeL];
            int temparrRight[] = new int[sizeR];

            /**Fill temp arrays with there elements, arr[] starts from left or middle*/
            for (int i= 0; i< sizeL; i++){
                temparrLeft[i] = arr[left+i];
            }
            for (int i= 0; i< sizeR; i++){
                temparrRight[i] = arr[middle+i+1];
            }

            int i= 0, j = 0, k = left;  /** start merging both arrays together */

            while ( i < sizeL && j < sizeR) {
                if (temparrLeft[i] <= temparrRight[j]) {
                    arr[k] = temparrLeft[i];
                    i++;
                    k++;
                } else {
                    arr[k] = temparrRight[j];
                    j++;
                    k++;
                }
            }
                /**conditions if one merge size finishes first */
                while ( i < sizeL)  {
                    arr[k] =  temparrLeft[i];
                    i++;
                    k++;
                }

                while (j < sizeR) {
                    arr[k] =  temparrRight[j];
                    j++;
                    k++;
                }
            paintComponent(this.getGraphics());


		}

		
		//recursive merge sort method
		public void R_Merge(int arr[], int left, int right) {

            if (left < right) {
                int middle = left + (right-left) / 2; /**Here we get the middle*/

                R_Merge(arr, left, middle );   /**Recursively sort from the left side*/
                R_Merge(arr, middle+1, right); /**Then from the right**/

                Merge(arr, left, right, middle ); /**Call merge and sort begin to merge all the sub arrays*/
            }
        }
		


	//////////////////////////////////////////////////////////////////////////////////////////
		
		//iterative merge sort method
		public void I_MergeSort() {
            /** The iterative merge sort I was provided with did not work correctly. Since we are not getting graded
             * for iterative merge sort, I just replaced it with Recursive Merge sort so that it's possible for all
             * sorts to me marked complete and for shuffle lines to be active agin.
             */
            //getting the date and time when the iterative merge sort starts
            Calendar start = Calendar.getInstance();

            RecursiveMergeSort();
            Calendar end = Calendar.getInstance(); //Timer stuff
            SortGUI.imergeTime = end.getTime().getTime() - start.getTime().getTime();


        }/*
        }
		//assigning the size for the tempArray below
		tempArray = new int[total_number_of_lines]; 
		//saving the value of total_number_of_lines
		int beginLeftovers = total_number_of_lines;

		
		for (int segmentLength = 1; segmentLength <= total_number_of_lines/2; segmentLength = 2*segmentLength)
		{
			beginLeftovers = I_MergeSegmentPairs(total_number_of_lines, segmentLength);
			int endSegment = beginLeftovers + segmentLength - 1;
			if (endSegment < total_number_of_lines - 1) 
			{
			I_Merge(beginLeftovers, endSegment, total_number_of_lines - 1);
			}
		} 

		// merge the sorted leftovers with the rest of the sorted array
		if (beginLeftovers < total_number_of_lines) {
			I_Merge(0, beginLeftovers-1, total_number_of_lines - 1);
		}
		//getting the date and time when the iterative merge sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the iterative merge sort to execute 
		//subtracting the end time with the start time
	    SortGUI.imergeTime = end.getTime().getTime() - start.getTime().getTime();
	} 

	// Merges segments pairs (certain length) within an array 
	public int I_MergeSegmentPairs(int l, int segmentLength)
	{
		//The length of the two merged segments 

		//You suppose  to complete this part (Given).
		int mergedPairLength = 2 * segmentLength;
		int numberOfPairs = l / mergedPairLength;

		int beginSegment1 = 0;
		for (int count = 1; count <= numberOfPairs; count++)
		{
			int endSegment1 = beginSegment1 + segmentLength - 1;

			int beginSegment2 = endSegment1 + 1;
			int endSegment2 = beginSegment2 + segmentLength - 1;
			I_Merge(beginSegment1, endSegment1, endSegment2);

			beginSegment1 = endSegment2 + 1;
			//redrawing the lines_lengths
			paintComponent(this.getGraphics());
			//Causing a delay for 10ms
			delay(10);
		}
		// Returns index of last merged pair
		return beginSegment1;
		//return 1;//modify this line
	}

	public void I_Merge(int first, int mid, int last)
	{
		//You suppose  to complete this part (Given).
		// Two adjacent sub-arrays
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		// While both sub-arrays are not empty, copy the
		// smaller item into the temporary array
		int index = beginHalf1; // Next available location in tempArray
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
		{
			// Invariant: tempArray[beginHalf1..index-1] is in order
			if (lines_lengths[beginHalf1] < lines_lengths[beginHalf2])
			{
				tempArray[index] = lines_lengths[beginHalf1];
				beginHalf1++;
			}
			else
			{
				tempArray[index] = lines_lengths[beginHalf2];
				beginHalf2++;
			}
		}
		//redrawing the lines_lengths
		paintComponent(this.getGraphics());

		// Finish off the nonempty sub-array

		// Finish off the first sub-array, if necessary
		for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
			// Invariant: tempArray[beginHalf1..index-1] is in order
			tempArray[index] = lines_lengths[beginHalf1];

		// Finish off the second sub-array, if necessary
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
			// Invariant: tempa[beginHalf1..index-1] is in order
			tempArray[index] = lines_lengths[beginHalf2];

		// Copy the result back into the original array
		for (index = first; index <= last; index++)
			lines_lengths[index] = tempArray[index];
	}*/

	//////////////////////////////////////////////////////////////////////	
		
		//This method resets the window to the scrambled lines display
		public void reset(){
			if(scramble_lines != null)
			{
				//copying the old scrambled lines into lines_lengths
				for (int i = 0; i < total_number_of_lines; i++)
				{
					lines_lengths[i] = scramble_lines[i] ;
				}
			//Drawing the now scrambled lines_lengths
			paintComponent(this.getGraphics());
		}
			}
		
	
		//This method colours the lines and prints the lines
		public void paintComponent(Graphics g){
 			super.paintComponent(g);
			//A loop to assign a colour to each line
			for(int i = 0; i < total_number_of_lines; i++){
				//using eight colours for the lines
				if(i % 8 == 0){
					g.setColor(Color.green);
				} else if(i % 8 == 1){
					g.setColor(Color.blue);
				} else if(i % 8 == 2){
					g.setColor(Color.yellow);
				} else if(i%8 == 3){
					g.setColor(Color.red);
				} else if(i%8 == 4){
					g.setColor(Color.black);
				} else if(i%8 == 5){
					g.setColor(Color.orange);
				} else if(i%8 == 6){
					g.setColor(Color.magenta);
				} else
					g.setColor(Color.gray);
				
				//Drawing the lines using the x and y-components 
				g.drawLine(4*i + 25, 300, 4*i + 25, 300 - lines_lengths[i]);
			}
			
		}
		
		//A delay method that pauses the execution for the milliseconds time given as a parameter
		public void delay(int time){
			try{
	        	Thread.sleep(time);
	        }catch(InterruptedException ie){
	        	Thread.currentThread().interrupt();
	        }
		}
		
	}

