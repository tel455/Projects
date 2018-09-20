<!DOCTYPE html>
<!--Thi Le
    TEL455-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <title>Edit Listing</title>
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
    <h2>Edit Listing</h2>
    <form action="listSuccess.php" method="get">
        Item Name: <input type="text" value="Mountain Dew Cans(12)" required="required"></input>
        <br></br>
        Item Description: <input type="text" value="12 Empty Mountain Dew Cans" required="required"></input>
        <br></br>
        Starting Bid: $<input type="number" value="5.00" required="required"></input>
        <br></br>
        Please enter a new end date:
        <br></br>
        <!--type="datetime-local doesn't work on firefox or internet explorer, google chrome, safari, and opera are okay."-->
        <!--taken from http://www.w3schools.com/html/html_form_input_types.asp -->
        Listing End Date: <input type="datetime-local" required="required"></input>
        <br></br>
        <button onclick="return confirm('Please reconfirm if the edited information is correct.');">List it again!</button>
    </form>
</body>
</html>
