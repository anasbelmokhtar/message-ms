use TwitterLikeApp;

create table TwitterLikeApp.Users(
     UserId int not null primary key auto_increment,
     Username varchar(55) not null,
     FirstName varchar(45) not null,
     LastName varchar(55) not null,
     UserAddress varchar(85) not null,
     City varchar(45) not null,
     Country varchar(45) not null,
     PostalCode varchar(10) not null,
     UserRole varchar(12) not null,
     Token varchar(55),
     UserStatus varchar(20) default 'pending'
);

create table TwitterLikeApp.Messages(
    MessageId int not null primary key auto_increment,
    MessageName varchar(75),
    MessageContent varchar(1024),
    UserId int,
    FOREIGN KEY (UserId) REFERENCES TwitterLikeApp.Users(UserId)
);

create table TwitterLikeApp.Subscription (
    ProducerId int,
    SubscriberId int,
    SubscriptionDate Date not null,
    foreign key (ProducerId) references TwitterLikeApp.Users(UserID),
    foreign key (SubscriberId) references TwitterLikeApp.Users(UserID)
);



