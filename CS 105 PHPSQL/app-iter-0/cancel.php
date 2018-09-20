<!DOCTYPE html>
<!--Thi Le
    TEL455-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <title>Cancel Listing</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
    <h1>E-Bae</h1>
    <img src="gavel.jpg" alt="gavel" style="width:304px;height:228px;"></img>
    <nav id="siteNavigator">
        <ul role="directory">
            <li>
                <a href="success.php">E-Bae</a>
            </li>
            <li>
                <a href="list.php">List an Item</a>
            </li>
            <li>
                <a href="cancel.php">Cancel Listing</a>
            </li>
            <li>
                <a href="browse.php">Browse Listings</a>
            </li>
            <li>
                <a href="bid.php">Place Bid</a>
            </li>
            <li>
                <a href="close.php">Close Listing</a>
            </li>
            <li>
                <a href="pay.php">Pay</a>
            </li>
            <li>
                <a href="update.php">Update Listing</a>
            </li>
            <li>
                <a href="terms.php">Terms and Conditions</a>
            </li>
            <li class="placement">
                <a href="index.php">Logout</a>
            </li>
        </ul>
    </nav>
    <h2>Cancel Listing</h2>
    <p>Your Active Listings</p>
    <table>
        <thead>
            <tr>
                <th>Seller</th>
                <th>Item</th>
                <th>Description</th>
                <th>Starting Bid</th>
                <th>Current Bid</th>
                <th>Buy It Now</th>
                <th>Time Left</th>
                <th>Deadline</th>
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>TEL455</td>
                <td>Mountain Dew Cans(12)</td>
                <td>12 Empty Mountain Dew Cans</td>
                <td>$5.00</td>
                <td>$5.00</td>
                <td>$100.00</td>
                <!--Figure out how to embed a timer-->
                <td>1 day 3 hr 30 min</td>
                <td>02/19/2016 04:30 PM</td>
                <td>OPEN</td>
                <td>
                    <form action="cancelSuccess.php" method="get">
                        <!--taken from http://stackoverflow.com/questions/9139075/confirm-message-before-delete-->
                        <button onclick="return confirm('Are you sure you want to delete this listing?');">Cancel Listing</button>
                    </form>
                </td>
            </tr>
        </tbody>
    </table>
    <p>Inactive Listings</p>
    <table>
        <thead>
            <tr>
                <th>Seller</th>
                <th>Item</th>
                <th>Description</th>
                <th>Starting Bid</th>
                <th>Current Bid</th>
                <th>Buy It Now</th>
                <th>Time Left</th>
                <th>Deadline</th>
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>TEL455</td>
                <td>Pepsi Cans(12)</td>
                <td>12 Empty Pepsi Cans</td>
                <td>$5.00</td>
                <td>$30.00</td>
                <td>$100.00</td>
                <!--Figure out how to embed a timer-->
                <td>0 day 0 hr 0 min</td>
                <td>01/16/2016 08:00 PM</td>
                <td>CLOSED</td>
                <td>
                    <form method="get">
                        <!--taken from http://stackoverflow.com/questions/9139075/confirm-message-before-delete-->
                        <button onclick="return confirm('This listing cannot be deleted as it has already ended!')">Cancel Listing</button>
                    </form>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>
