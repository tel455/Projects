<?php

require_once '/u/thi/openDatabase.php';

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
        FROM AUCTION;
SQL
);

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
    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>Seller</th>
                <th>Item</th>
                <th>Description</th>
                <th>Current Bid</th>
                <th>Buy It Now</th>
                <th>Close Time</th>
                <th>Status</th>
                <th>Details</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <?php
                foreach ($personQuery->fetchAll() as $person) {
            ?>
            <tr>
                <td><?= $person['AUCTION_ID']?></td>
                <td><?= $person['NAME']?></td>
                <td><?= $person['ITEM_CAPTION']?></td>
                <td><?= $person['ITEM_DESCRIPTION']?></td>
                <td>$<?= $person['CURRENT_BID']?></td>
                <td>$<?= $person['BUY_NOW']?></td>
                <td><?= $person['CLOSE_TIME']?></td>
                <td><?= $person['STATUS']?></td>
                <td><a href="itemDetails.php?id= <?= $person['AUCTION_ID']?>">Details</a></td>
                <td>
                    <form action="bid.php" method="get">
                        <button>Bid!</button>
                    </form>
                </td>
                <td>
                    <form> <!--action="tbd" method="get"-->
                        <button>Buy it now!</button>
                    </form>
                </td>
            </tr>
            <?php
                }
                $personQuery->closeCursor();
            ?>
        </tbody>
    </table>
</body>
</html>
