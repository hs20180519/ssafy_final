-- 관심 매물 지역 조회시 사용
DROP VIEW IF EXISTS `ssafyhome`.`favorite_property_details`;

CREATE VIEW `ssafyhome`.`favorite_property_details` AS
SELECT f.id AS favorite_id,
       f.member_id,
       f.apt_seq,

       h.apt_nm,

       -- Land address (지번 주소)
       CONCAT(
               d.sido_name, " ",
               d.gugun_name, " ",
               h.umd_nm, " ",
               h.jibun
       )    AS land_address,

       -- Road address (도로명 주소)
       CONCAT(
               d.sido_name, " ",
               d.gugun_name, " ",
               h.road_nm, ' ',
               h.road_nm_bonbun,
               CASE
                   WHEN h.road_nm_bubun != '0'
                       THEN CONCAT('-', h.road_nm_bubun)
                   ELSE ''
                   END
       )    AS road_address,

       -- Deal information (거래 정보)
       t.deal_amount,
       t.deal_year,
       t.deal_month,
       t.deal_day
FROM `favorite_properties` f
         JOIN
     `house_infos` h
     ON f.apt_seq = h.apt_seq

         JOIN
     `dong_codes` d
     ON CONCAT(h.sgg_cd, h.umd_cd) = d.dong_code

         LEFT JOIN
     `house_deals` t
     ON f.apt_seq = t.apt_seq
         AND CONCAT(
                     t.deal_year,
                     LPAD(t.deal_month, 2, '0'),
                     LPAD(t.deal_day, 2, '0')
             ) = (SELECT MAX(
                                 CONCAT(deal_year,
                                        LPAD(deal_month, 2, '0'),
                                        LPAD(deal_day, 2, '0'))
                         )
                  FROM `house_deals`
                  WHERE apt_seq = f.apt_seq)
;
