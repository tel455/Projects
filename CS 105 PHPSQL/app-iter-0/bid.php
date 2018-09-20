<?php
require_once '/u//thi/openDatabase.php';

?>

<!DOCTYPE html>
<!--Thi Le
    TEL455-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <title>Bid</title>
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
    <h2>You are currently bidding for:</h2>
    <table class="table">
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
                <td>Donations</td>
                <td>Broke college student looking for donations</td>
                <td>$5.00</td>
                <td>$5.00</td>
                <td>$100000.00</td>
                <!--Figure out how to embed a timer-->
                <td>1 day 3 hr 30 min</td>
                <td>02/19/2016 04:30 PM</td>
                <td>OPEN</td>
                <td>
                    <form action="bidSuccess.php" method="get">
                        <input type="number" min="5.00" max="100000.00" required="required"></input>
                        <button onclick="return confirm('Are you sure you want to bid this amount?')">Bid</button>
                    </form>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>
