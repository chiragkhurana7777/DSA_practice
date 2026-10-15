# Write your MySQL query statement below

(select u.name as results from movierating mr join users u on mr.user_id=u.user_id group by u.user_id,u.name order by count(*) desc,u.name  limit 1)

union all 

(select m.title as results from movierating mr join movies m on mr.movie_id = m.movie_id
WHERE mr.created_at >= '2020-02-01' AND mr.created_at < '2020-03-01' 
group by m.movie_id,m.title order by avg(mr.rating) desc , m.title asc limit 1);