insert into account(account_Id)
values('E1');

insert into position(asset_id, quantity)
values('S1', 100);
insert into position(asset_id, quantity)
values('S3', 100);
insert into position(asset_id, quantity)
values('S4', 100);

insert into account_position(account_id, position_id)
values(1, 1);
insert into account_position(account_id, position_id)
values(1, 2);
insert into account_position(account_id, position_id)
values(1, 3);