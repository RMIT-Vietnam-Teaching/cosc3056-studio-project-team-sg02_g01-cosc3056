--Since the countries are added from "GlobalYearlyLandTemp..." .csv files which has no country codes,
    -- checking for records with NULL country code is needed
--Keeping running "AddDataCountries.java" and this, 
    --set codes for countries with NULL codes until there's no countries with NULL code
SELECT * FROM countries WHERE code IS NULL;
UPDATE countries SET code = 'ATR' WHERE name = 'Antarctica';