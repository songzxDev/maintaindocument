UPDATE maintain_document_investment_other
SET SHENGFENZONGJINGLI = REPLACE (
	SHENGFENZONGJINGLI,
	'蔡全根',
	'沈洪波'
)
WHERE
	SHENGFENZONGJINGLI_LOGIN_ID = 'caiquangen';
-- 
UPDATE maintain_document_investment_other
SET SHENGFENZONGJINGLI_LOGIN_ID='shenhongbo'
WHERE
	SHENGFENZONGJINGLI_LOGIN_ID = 'caiquangen';

