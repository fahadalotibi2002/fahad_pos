# The Fahad_POS Java class encapsulates the functionality of a comprehensive Point of Sale (POS) system designed for a grocery store. This system is structured to streamline essential operations, leveraging object-oriented principles for efficient management of sales, inventory updates, and sales reporting. The code incorporates exception handling and user input validation to enhance robustness and user experience.

Upon execution, the program initializes a GroceryStore object, establishing a connection to the inventory file named "inventory1.txt." The user is greeted with a welcoming message, and an intuitive menu is presented, offering distinct options for various aspects of grocery store management.

The menu-driven system enables users to seamlessly initiate the sales process, updating the inventory dynamically as transactions occur. The processSale method, invoked upon selecting option 1, facilitates a smooth and user-friendly sales experience. It incorporates a loop mechanism to handle scenarios where users need to navigate back within the sales process, ensuring flexibility and ease of use.

Option 2 empowers users to update and track inventory efficiently. The updateInventory method manages inventory modifications, providing real-time insights into stock levels. Similar to the sales process, a loop is employed to allow users to iteratively make inventory updates until they choose to exit.

For comprehensive business intelligence, option 3 triggers the generateSaleReport method, allowing users to access detailed sales reports. The system employs a loop to cater to scenarios where users wish to review multiple reports, creating a seamless and iterative reporting experience.
