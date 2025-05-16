alter table public.member
drop column role;

alter table public.member
	add nickname varchar(100) not null;

alter table public.member
	add sns_type varchar(20) not null;

alter table public.member
	add sns_id varchar(255) not null;

alter table public.member
	add profile_image_url text not null;

alter table public.member
	add is_active boolean;

alter table public.member
	add last_login_at timestamp(6);