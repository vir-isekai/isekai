alter table public.member
	rename column is_active to status;

alter table public.member
alter column status type varchar(20) using status::varchar(20);

alter table public.member
	alter column status set not null;