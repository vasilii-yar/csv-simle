DROP TABLE IF EXISTS public.objects;
CREATE TABLE public.objects(
	ssoid varchar(50) not null,
	times bigint,
	grpEvent varchar(250),
	typeEvent varchar(250),
	subtypeEvent varchar(250),
	url varchar(250),
	orgId varchar(250),
	formId varchar(250),
	sessionKey varchar(250),
	serviceResp varchar(250),
	eventdate bigint
);