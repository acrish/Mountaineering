CREATE DEFINER=`root`@`localhost` PROCEDURE `latestExpeditions`()
BEGIN

     DECLARE pid INT;
     DECLARE eid INT;
     DECLARE mid INT;
     DECLARE rstart DATE;
     DECLARE rend DATE;
     DECLARE mname VARCHAR(255);
     DECLARE ename VARCHAR(255);
     DECLARE expstart VARCHAR(255);
     DECLARE expend VARCHAR(255);
     DECLARE pname VARCHAR(255);
     DECLARE pdate DATE;
     DECLARE exit_loop BOOLEAN;

     DECLARE regs CURSOR FOR
             SELECT expedition_id, participant_id, start_date, end_date
             FROM registries
             WHERE start_date > STR_TO_DATE('2012-09-01', '%Y-%m-%d');

     DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = TRUE;

     OPEN regs;

     DELETE FROM latest_exp;
     regs_loop: LOOP
                IF  exit_loop THEN
                    LEAVE regs_loop;
                END IF;

                FETCH regs INTO eid, pid, rstart, rend;
                SELECT name, birth_date INTO pname, pdate
                FROM participants
                WHERE participant_id = pid;

                SELECT expname, start_village, end_village, map_id
                       INTO ename, expstart, expend, mid
                FROM expeditions
                WHERE expid = eid;

                SELECT mountain INTO mname
                FROM expeditionmaps
                WHERE mid = map_id;

                INSERT INTO latest_exp(mname, ename, expstart, expend, pname, pdate)
                       VALUES (mname, ename, expstart, expend, pname, pdate);

     END LOOP regs_loop;
     CLOSE regs;


END

