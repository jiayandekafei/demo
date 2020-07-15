z350_J9U9203A_list.sub('doc_print',
function($common, p, subKey) {

	var _pu = {libName :
		'アプリテンプレート/メインドキュメント印刷'};
	var _pr = {};
	var _co = {};
	var $$ = $common.debuggerLoader(true);
	$common.regLib(p.co.sid[subKey], _pu);

	_co.target = ['doc_main'];

	//ドキュメント画面のタイトル
	_pu.title = function() {
		return p.pr.title(p.co.sid[_co.target]);
	};

	//対象リソース
	_pu.targetRes = function() {
		return p.pr.targetRes(p.co.sid[subKey]);
	};

	//仮想リソース
	_pu.createTempRes = function () {
		var docResId = z350_COMMON.getParamValue('docResId');
		return $common.createTempResKeepFID(
			p.co.sid[subKey],
			docResId,
			p.co.sid['doc_main']
		);
	};


	Object.freeze(_co);
	Object.freeze(_pr);
	return Object.freeze(_pu);

});