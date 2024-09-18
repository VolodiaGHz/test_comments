insert into public.users(id, body, "postId", username, "updatedAt")
values (:id,:body,:postId,:username,:updatedAt::timestamp)
on conflict(id) do update set "updatedAt"=:updatedAt::timestamp