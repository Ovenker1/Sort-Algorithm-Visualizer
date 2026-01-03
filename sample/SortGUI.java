/**
 *
 * @author Ouda
 */

//importing the libraries that will be needed in this program

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

//the class with button and main method
public class SortGUI {

	// import javax.swing.JFrame;
    /**Importing a hashmap to better control which algorithms have been ran and which haven't*/
    Map<String, Boolean> sortDone = new HashMap<>();





    private void initSortMap() {
        sortDone.put("selection", false);
        sortDone.put("merge_recursive", false);
        sortDone.put("merge_iterative", false);
        sortDone.put("quick", false);
        sortDone.put("shell", false);
        sortDone.put("insertion", false);
        sortDone.put("bubble", false);
        sortDone.put("radix", false);
    }


	//a variable that holds the amount of time for the selection sort takes to execute
	public static double selectionTime = 0.0;
	//a variable that holds the amount of time for the recursive merge sort takes to execute
	public static double rmergeTime = 0.0;
	//a variable that holds the amount of time for the iterative merge sort takes to execute
	public static double imergeTime = 0.0;
	//Boolean variable that is made to keep track whether or not the selection sort has already been used
    /** my additions*/
    public static double bubbleTime = 0.0;
    public static double insertionTime = 0.0;
    public static double shellTime = 0.0;
    public static double radixTime = 0.0;
    public static double quickTime = 0.0;

	public boolean Selection_Done = false;
	//Boolean variable that is made to keep track whether or not the recursive merge sort has already been used
	public boolean Recersive_Merge_Done = false;
	//Boolean variable that is made to keep track whether or not the iterative merge sort has already been used
	public boolean Iterative_Merge_Done = false;
	//Making a object from the class SortShow
    /**My additions*/
    public boolean Bubble_Done = false;
    public boolean Insertion_Done = false;
    public boolean Shell_Done = false;
    public boolean Radix_Done = false;
    public boolean Quick_Done = false;

	SortShow sortArea = new SortShow();
	
	//Default constructor for SortGUI
	public SortGUI() {
		//making a MyScreen object
        initSortMap();
		// You need to adjust the following values to your Screen dimensions

		MyScreen screen = new MyScreen();
		//Setting a title to the GUI window
		screen.setTitle("Assignment-1 by Abdelnasser Ouda");
		//setting the size of the window 
		screen.setSize(975+sortArea.total_number_of_lines, 700);
		//the operation when the frame is closed
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//is set to true to display the frame
		screen.setVisible(true);
	}
	//A public class that extends JFrame
	public class MyScreen extends JFrame {
		//making a scramble button with a text "Scramble Lines" on it
		JButton scramble_button = new JButton("Scramble Lines");
		//making a selection button with a text "Selection" on it
		JRadioButton selection = new JRadioButton("Selection");
		//making a recursive merge button with a text "Scramble Lines" on it
		JRadioButton rmerge = new JRadioButton("MergeSort Recursive");
		//making a iterative merge button with a text "Selection" on it
		JRadioButton imerge = new JRadioButton("BrokeSort Iterative");
		//making a reset button with a text "Selection" on it
		JRadioButton reset = new JRadioButton("Reset");
		//A label that displays the time it took for the Selection sort took to execute
        /** Added 5 JRadioButtons to go with the above^*/

        JRadioButton bubble = new JRadioButton("BubbleSort");
        JRadioButton insertion = new JRadioButton("Insertion");
        JRadioButton shell = new JRadioButton("Shell");
        JRadioButton quick = new JRadioButton("Quick");
        JRadioButton radix = new JRadioButton("Radix");

        /** adding Jlabels for 5 other sorts */
        JLabel bubble_time_label = new JLabel("Bubble Time");
        JLabel bubble_time_taken = new JLabel("");

        JLabel insertion_time_label = new JLabel("Insertion Time");
        JLabel insertion_time_taken = new JLabel("");

        JLabel quick_time_label = new JLabel("Quick Time");
        JLabel quick_time_taken = new JLabel("");

        JLabel shell_time_label = new JLabel("Shell Time");
        JLabel shell_time_taken = new JLabel("");

        JLabel radix_time_label = new JLabel("Radix Time");
        JLabel radix_time_taken = new JLabel("");

		JLabel selection_time_label = new JLabel("Selection Time");
		JLabel selection_time_taken = new JLabel(""); 
		//A label that displays the time it took for the recursive merge sort took to execute 
		JLabel rmerge_time_label = new JLabel("MergeSort-Rec Time");
		JLabel rmerge_time_taken = new JLabel("");
		//A label that displays the time it took for the iterative merge sort took to execute
		JLabel imerge_time_label = new JLabel("MergeSort-Ita Time");
		JLabel imerge_time_taken = new JLabel("");
	
		//the default constructor for the class MyScreen
		public MyScreen() {
			// Panel where sorted lines_lengths will displayed
			//The time displayed for selection sort will be the colour red
			selection_time_taken.setForeground(Color.RED);
			//The time displayed for recursive merge sort will be the colour red
			rmerge_time_taken.setForeground(Color.RED);
			//The time displayed for iterative merge sort will be the colour red
			imerge_time_taken.setForeground(Color.RED);
			//The selection button text will be the colour blue
            shell_time_taken.setForeground(Color.RED); /** ADDING MY SORTS*/
            quick_time_taken.setForeground(Color.RED);
            insertion_time_taken.setForeground(Color.RED);
            bubble_time_taken.setForeground(Color.RED);
            radix_time_taken.setForeground(Color.RED);

			selection.setForeground(Color.BLUE);
			//The recursive merge button text will be the colour blue
			rmerge.setForeground(Color.BLUE);
			//The iterative merge button text will be the colour blue
			imerge.setForeground(Color.BLUE);
			//The scramble button's text will be blue
            radix.setForeground(Color.BLUE);/**Adding my own sorts to match*/
            quick.setForeground(Color.BLUE);
            insertion.setForeground(Color.BLUE);
            shell.setForeground(Color.BLUE);
            bubble.setForeground(Color.BLUE);
			scramble_button.setForeground(Color.BLUE);
			//setting the font of scramble button
			scramble_button.setFont(new Font("Arial", Font.BOLD, 15));
			//A Panel to hold the radio_button_selection and set the GridLayout
			JPanel radio_button_selection_Panel = new JPanel(new GridLayout(8, 1, 15, -1));

			//Adding the selection button to the radio_button_selection_Panel
			radio_button_selection_Panel.add(selection);
			//Adding the recursive merge button to the radio_button_selection_Panel
			radio_button_selection_Panel.add(rmerge);
			//Adding the iterative merge button to the radio_button_selection_Panel
			radio_button_selection_Panel.add(imerge);
			//Adding the reset button to the radio_button_selection_Panel

			//giving the radio_button_selection_Panel a border with a title
            /** adding my buttons to the list*/
            radio_button_selection_Panel.add(bubble);
            radio_button_selection_Panel.add(insertion);
            radio_button_selection_Panel.add(shell);
            radio_button_selection_Panel.add(quick);
            radio_button_selection_Panel.add(radix);
            radio_button_selection_Panel.add(reset);

			radio_button_selection_Panel.setBorder(new javax.swing.border.TitledBorder("Sort Algorithms"));

			//A Panel to hold the time_Panel and set the GridLayout
			JPanel time_Panel = new JPanel(new GridLayout(8, 1, 0, 2));
			//Adding the selection_time_label to the time_Panel
			time_Panel.add(selection_time_label);
			//Adding the selection_time_taken to the time_Panel
			time_Panel.add(selection_time_taken);
			//Adding the rmerge_time_label to the time_Panel
			time_Panel.add(rmerge_time_label); 
			//Adding the rmerge_time_taken to the time_Panel
			time_Panel.add(rmerge_time_taken);
			//Adding the imerge_time_label to the time_Panel
			time_Panel.add(imerge_time_label);
			//Adding the imerge_time_taken to the time_Panel
			time_Panel.add(imerge_time_taken);
            /** Adding my sorts*/
            time_Panel.add(quick_time_label);
            time_Panel.add(quick_time_taken);
            time_Panel.add(shell_time_label);
            time_Panel.add(shell_time_taken);
            time_Panel.add(insertion_time_label);
            time_Panel.add(insertion_time_taken);
            time_Panel.add(bubble_time_label);
            time_Panel.add(bubble_time_taken);
            time_Panel.add(radix_time_label);
            time_Panel.add(radix_time_taken);

			//A Panel to hold the buttons_area_Panel and set the GridLayout
			//This buttons_area_Panel will hold scrambleButton, radio_button_selection and the time_Panel
			JPanel buttons_area_Panel = new JPanel(new GridLayout(5, 1, 5, 5));
			//adding scramble_button to the buttons_area_Panel
			buttons_area_Panel.add(scramble_button);
			//adding radio_button_selection_Panel to the buttons_area_Panel
			buttons_area_Panel.add(radio_button_selection_Panel);
			//adding time_Panel to the buttons_area_Panel
			buttons_area_Panel.add(time_Panel);

			//placing the buttons_area_Panel to the east side of the window
			add(buttons_area_Panel, BorderLayout.EAST);
			//placing the sortArea object in the center of the window
			add(sortArea, BorderLayout.CENTER);
            /// ///////////////////////////////////////////////////////////////
			//setting all booleans to false
			/*Set_Available_Chooses(false, false, false, false,false
            ,false,false,false,false);*/

			//The following code is for creating a listener for each GUI element 

			//Creating an action listener for scramble button
			//This button will be used to scramble the lines in a random way
			//this same scrambled lines will be used for all threes sort methods used in this program
			scramble_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Scrambling the lines_lengths array
					sortArea.scramble_the_lines(); 
					//Since it has already been clicked, it will no longer be enabled
					scramble_button.setEnabled(false); 
					//setting all booleans true except for reset
                    bubble.setEnabled(true);
                    insertion.setEnabled(true);
                    selection.setEnabled(true);
                    quick.setEnabled(true);
                    shell.setEnabled(true);
                    radix.setEnabled(true);
                    imerge.setEnabled(true);
                    rmerge.setEnabled(true);


				}
			});

			//Creating an action listener for selection button
			selection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Sorting the array in the selection sort method
					sortArea.SelectionSort(); 
					//Selection sort has finished/been clicked
                    sortDone.put("selection", true);
					//The amount of time taken for selection sort took
					selection_time_taken.setText(selectionTime / 1000 + " Seconds");
					//setting all booleans false except for reset
                    disableAllSortButtons();
                    reset.setEnabled(true);

                }
			});

			//Creating an action listener for recursive merge button
			rmerge.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Sorting the array in the recursive merge sort method
					sortArea.RecursiveMergeSort();
					//The amount of time taken for recursive merge sort took
					rmerge_time_taken.setText((rmergeTime / 1000) + " Seconds");
					//recursive merge sort has finished/been clicked
                    sortDone.put("merge_recursive", true);
					//setting all booleans false except for reset
                    disableAllSortButtons();
                    reset.setEnabled(true);
				}
			});
			
			//Creating an action listener for iterative merge button
			imerge.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Sorting the array in the iterative merge sort method
					sortArea.I_MergeSort();
					//The amount of time taken for iterative merge sort took
					imerge_time_taken.setText((imergeTime / 1000) + " Seconds");
					//iterative merge sort has finished/been clicked
					sortDone.put("merge_iterative", true);
					//setting all booleans false except for reset
                    disableAllSortButtons();
                    reset.setEnabled(true);
				}
			});
            /**Plugging in my own bubblesort method*/
            bubble.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sortArea.BubbleSort();
                    bubble_time_taken.setText((bubbleTime / 1000) + " Seconds");
                    sortDone.put("bubble", true);
                    disableAllSortButtons();
                    reset.setEnabled(true);
                }
            });

            insertion.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sortArea.InsertionSort();
                    insertion_time_taken.setText(insertionTime / 1000 + " Seconds");
                    sortDone.put("insertion", true);
                    disableAllSortButtons();
                    reset.setEnabled(true);
                }
            });

            shell.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sortArea.ShellSort();
                    shell_time_taken.setText(shellTime / 1000 + " Seconds");
                    sortDone.put("shell", true);
                    disableAllSortButtons();
                    reset.setEnabled(true);
                }
            });

            quick.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sortArea.QuickSort();
                    quick_time_taken.setText(quickTime / 1000 + " Seconds");
                    sortDone.put("quick", true);
                    disableAllSortButtons();
                    reset.setEnabled(true);
                }
            });

            radix.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sortArea.RadixSort();
                    radix_time_taken.setText(radixTime / 1000 + " Seconds");
                    sortDone.put("radix", true);
                    disableAllSortButtons();
                    reset.setEnabled(true);
                }
            });


			//Creating an action listener for reset button
			reset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//disabling reset since it was clicked
					reset.setEnabled(false);
					//reseting the lines_lengths to its scrambled lines
					sortArea.reset();

                    /**hashmap implementation*/
                    // Enable only the sorts that haven’t been done yet
                    for (Map.Entry<String, Boolean> entry : sortDone.entrySet()) {
                        enableSortButton(entry.getKey(), !entry.getValue());
                    }

                    // unlock scramble button if all sorts are done 
                    boolean allDone = sortDone.values().stream().allMatch(v -> v);
                    scramble_button.setEnabled(allDone);

                    System.out.println("test");
				}
			});

		}
		/**enable sorting buttons */
        private void enableSortButton(String sortName, boolean enabled) {
            System.out.println("Got here");
            switch (sortName) {
                case "selection": selection.setEnabled(enabled); break;
                case "merge_recursive": rmerge.setEnabled(enabled); break;
                case "merge_iterative": imerge.setEnabled(enabled); break;
                case "quick": quick.setEnabled(enabled); break;
                case "shell": shell.setEnabled(enabled); break;
                case "insertion": insertion.setEnabled(enabled); break;
                case "bubble": bubble.setEnabled(enabled); break;
                case "radix": radix.setEnabled(enabled); break;
            }
        }

		/**disables all sorting buttons */
        private void disableAllSortButtons() {
            selection.setEnabled(false);
            rmerge.setEnabled(false);
            imerge.setEnabled(false);
            quick.setEnabled(false);
            shell.setEnabled(false);
            insertion.setEnabled(false);
            bubble.setEnabled(false);
            radix.setEnabled(false);
        }


	}

	//The main method
	public static void main(String[] args) {
		//initialize the class
		SortGUI sort_GUI = new SortGUI();

	}

}


