drop table if exists VehicleInfoType cascade;
drop table if exists LossInfoType cascade;
drop table if exists Claim cascade;


create table Claim(
ClaimNumber varchar(200) primary key,
ClaimFirstName varchar(200),
ClaimLastName varchar(200),
Status varchar(10),
LossDate date,
AssignedAdjusterId integer,
CONSTRAINT checkStatus CHECK (status in ('OPEN', 'CLOSED'))
);

create table LossInfoType(
ClaimNumber varchar(200) primary key references claim(ClaimNumber) on delete cascade,
CauseOfLossCode varchar(200),
ReportedDate date,
LossDescription varchar(200),
CONSTRAINT checkLosscode CHECK (CauseOfLossCode in ('Collision', 'Explosion', 'Fire', 'Hail', 'Mechanical breakdown', 'Other'))
);

create table VehicleInfoType(
Vin varchar(200) primary key,
ClaimNumber varchar(200) references claim(ClaimNumber) on delete cascade,
ModelYear integer,
MakeDescription varchar(200),
ModelDescription varchar(200),
EngineDescription varchar(200),
ExteriorColor varchar(200),
LicPlate varchar(200),
LicPlateState varchar(200),
DamageDescription varchar(200),
LicPlateExpDate date,
Mileage integer,
);
commit;