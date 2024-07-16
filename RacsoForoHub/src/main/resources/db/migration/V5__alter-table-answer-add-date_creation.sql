ALTER TABLE answers ADD date_creation datetime;
update answers set date_creation = NOW();