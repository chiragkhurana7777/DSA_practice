with cte as (
    select person_name, turn,sum(weight) over(order by turn) as total_weight from queue
)
select person_name from cte where total_weight<=1000 order by turn desc limit 1;