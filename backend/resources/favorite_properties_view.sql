-- 관심 매물 지역 조회시 사용
DROP VIEW IF EXISTS `ssafyhome`.`favorite_property_details`;

CREATE OR REPLACE VIEW `ssafyhome`.`favorite_property_details` AS
WITH ranked_deals AS (
    SELECT 
        f.member_id,
        f.apt_seq,
        h.apt_nm,
        CONCAT(d.sido_name, " ", d.gugun_name, " ", h.umd_nm, " ", h.jibun) AS land_address,
        CONCAT(d.sido_name, " ", d.gugun_name, " ", h.road_nm, ' ', h.road_nm_bonbun, 
               CASE WHEN h.road_nm_bubun != '0' THEN CONCAT('-', h.road_nm_bubun) ELSE '' END) AS road_address,
        t.deal_amount,
        t.deal_year,
        t.deal_month,
        t.deal_day,
        ROW_NUMBER() OVER (
            PARTITION BY f.member_id, f.apt_seq
            ORDER BY 
                t.deal_year DESC, 
                t.deal_month DESC, 
                t.deal_day DESC, 
                t.deal_amount ASC
        ) AS rn
    FROM 
        `favorite_properties` f
    JOIN 
        `house_infos` h 
        ON f.apt_seq = h.apt_seq
    JOIN 
        `dong_codes` d 
        ON CONCAT(h.sgg_cd, h.umd_cd) = d.dong_code
    LEFT JOIN 
        `house_deals` t 
        ON f.apt_seq = t.apt_seq
)