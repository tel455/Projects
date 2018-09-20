<!DOCTYPE html>
<!--Thi Le
    TEL455-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <title>Pay For Your Purchase</title>
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
    <h2>Congratulations on your win! Please pay for your winning bid below.</h2>
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
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>ABC123</td>
                <td>2013 Toyota Camry</td>
                <td>20k Miles, clean title, runs perfect</td>
                <td>$11,500.00</td>
                <td>$16,800.00</td>
                <td>$25,000.00</td>
                <td>0 day 0 hr 0 min</td>
                <td>02/22/2016 10:30 AM</td>
                <td>CLOSED</td>
            </tr>
        </tbody>
    </table>
    <h2>Please provide your payment information.</h2>
    <p>Payment Amount: $16,800.00</p>
    <form action="success.php" method="get">
        <br></br>
        Your information will be kept secret!
        <br></br>
        First name: <input type="text" required="required"></input>
        <br></br>
        Last name: <input type="text" required="required"></input>
        <br></br>
        Billing Address:
        <br></br>
        Address 1: <input type="text" required="required"></input>
        <br></br>
        Address 2: <input type="text" required="required"></input>
        <br></br>
        City: <input type="text" required="required"></input>
        State: <input type="text" required="required"></input>
        Zip: <input type="number" required="required"></input>
        <br></br>
        Credit/Debit Card Number: <input type="number" required="required"></input>
        <br></br>
        CVV: <input type="number" required="required"></input>
        <br></br>
        Please confirm your password for security purposes.
        <br></br>
        Password: <input type="password" required="required"></input>
        <br></br>
        By clicking submit, you agree to our terms and conditions(above).
        <!--taken from http://stackoverflow.com/questions/9139075/confirm-message-before-delete-->
        <button onclick="return confirm('Please confirm if your information is correct and press OK. You will be redirected ' +
         'to the home page if payment is successful.');">Submit</button>
    </form>
</body>
</html>
