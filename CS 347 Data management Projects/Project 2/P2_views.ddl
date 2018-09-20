drop view P2_Horse_view;
drop view P2_Bird_view;
drop view P2_Fish_view;
drop view P2_Dog_view;

--horse
create view P2_Horse_view as
SELECT
		animal_id,
		name,
		species,
		weight,
		color,
		height,
		has_been_tamed,
		has_horseshoes,
		mane_length,
		type
from P2_Animal where type = 'P2_Horse';

create or replace TRIGGER P2_Horse_trigger
	 INSTEAD OF insert ON P2_Horse_view
	 FOR EACH ROW
BEGIN
	insert into P2_Animal(
		name,
		species,
		weight,
		color,
		height,
		has_been_tamed,
		has_horseshoes,
		mane_length,
		type)
	VALUES (
		:NEW.name,
		:NEW.species,
		:NEW.weight,
		:NEW.color,
		:NEW.height,
		:NEW.has_been_tamed,
		:NEW.has_horseshoes,
		:NEW.mane_length,
		'P2_Horse') ;
END;
/

--bird
create view P2_Bird_view as
SELECT
		animal_id,
		name,
		species,
		weight,
		color,
		height,
		wingspan,
		beak_shape,
		can_swim,
		can_fly,
		type
FROM P2_Animal where type = 'P2_Bird';

create or replace TRIGGER P2_Bird_trigger
	 INSTEAD OF insert ON P2_Bird_view
	 FOR EACH ROW
BEGIN
	insert into P2_Animal(
		name,
		species,
		weight,
		color,
		height,
		wingspan,
		beak_shape,
		can_swim,
		can_fly,
		type)
	VALUES (
		:NEW.name,
		:NEW.species,
		:NEW.weight,
		:NEW.color,
		:NEW.height,
		:NEW.wingspan,
		:NEW.beak_shape,
		:NEW.can_swim,
		:NEW.can_fly,
		'P2_Bird') ;
END;
/

--fish
create view P2_Fish_view as
SELECT
		animal_id,
		name,
		species,
		weight,
		color,
		height,
		number_of_fins,
		fin_length,
		has_scales,
		type
FROM P2_Animal where type = 'P2_Fish';

create or replace TRIGGER P2_Fish_trigger
	 INSTEAD OF insert ON P2_Fish_view
	 FOR EACH ROW
BEGIN
	insert into P2_Animal(
		name,
		species,
		weight,
		color,
		height,
		number_of_fins,
		fin_length,
		has_scales,
		type)
	VALUES (
		:NEW.name,
		:NEW.species,
		:NEW.weight,
		:NEW.color,
		:NEW.height,
		:NEW.number_of_fins,
		:NEW.fin_length,
		:NEW.has_scales,
		'P2_Fish') ;
END;
/

--dog
create view P2_Dog_view as
SELECT
		animal_id,
		name,
		species,
		weight,
		color,
		height,
		tail_length,
		breed,
		is_face_smashed_in,
		type
FROM P2_Animal where type = 'P2_Dog';

create or replace TRIGGER P2_Dog_trigger
	 INSTEAD OF insert ON P2_Dog_view
	 FOR EACH ROW
BEGIN
	insert into P2_Animal(
		name,
		species,
		weight,
		color,
		height,
		tail_length,
		breed,
		is_face_smashed_in,
		type)
	VALUES (
		:NEW.name,
		:NEW.species,
		:NEW.weight,
		:NEW.color,
		:NEW.height,
		:NEW.tail_length,
		:NEW.breed,
		:NEW.is_face_smashed_in,
		'P2_Dog') ;
END;
/