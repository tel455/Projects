<?php
error_reporting(-1);
ini_set("display_errors", 1);

require_once '/u//thi/openDatabase.php';

$personQuery = $database->prepare(<<<'SQL'
    SELECT 
        AUCTION_ID,
        NAME,
        ITEM_CAPTION,
        ITEM_DESCRIPTION,
        CURRENT_BID,
        BUY_NOW,
        OPEN_TIME,
        CLOSE_TIME,
        STATUS
        FROM AUCTION
        WHERE AUCTION_ID = :auctionId;
        
SQL
);
$personQuery->bindValue(':auctionId', $_GET['id'], PDO::PARAM_INT);
$personQuery->execute();
?>

<!DOCTYPE html>
<!--Thi Le
    TEL455-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <title>Browse Listings</title>
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
    <h2>Browse:
        <input type="search"></input>
        <input type="submit" value="Search"></input>
    </h2>
    <p>Sort by:
        <!--Dead Links, TBD-->
        <a href="">Most Recent</a>
        <a href="">Price:LO-HI</a>
        <a href="">Price:HI-LO</a>
    </p>
    <img class="bear" src="bear.jpg" alt="example photo" style="width:300px;height:250px;"></img>
    <img class="bear" src="bear.jpg" alt="example photo" style="width:300px;height:250px;"></img>
    <?php
        $person = $personQuery->fetch();
    ?>
    <p class="detail">Seller Info</p>
        <?= $person['NAME']?>
    <p class="detail">Item</p>
        <?= $person['ITEM_CAPTION']?>
    <p class="detail">Description</p>
        <?= $person['ITEM_DESCRIPTION']?>
    <p class="detail">Pricing</p>
        Current Bid: $<?= $person['CURRENT_BID']?>
    <p></p>
        Buy It Now: $<?= $person['BUY_NOW']?>
    <p class="detail">Open Time</p>
        <?= $person['OPEN_TIME']?>
    <p class="detail">Close Time</p>
        <?= $person['CLOSE_TIME']?>
    <p class="detail">Status</p>
        <?= $person['STATUS']?>
    <p></p>
    <form action="bid.php" method="get">
        <button>Bid!</button>
    </form>
    <form> <!--action="tbd" method="get"-->
        <button>Buy it now!</button>
    </form>
    <?php
        $personQuery->closeCursor();
    ?>
</body>
</html>
