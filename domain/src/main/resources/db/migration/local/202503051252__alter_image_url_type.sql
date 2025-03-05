alter table public.agency
alter column logo_image_url type text using logo_image_url::text;

alter table public.fandom
alter column logo_image_url type text using logo_image_url::text;

