z350_J9U9203A_list.sub('doc_main_hide',
function($common, p, subKey) {

	var _pu = {libName : 'アプリテンプレート/メインドキュメント非表示領域'};
	var _pr = {};
	var _co = {};
	var $$ = $common.debuggerLoader(true);
	$common.regLib(p.co.sid[subKey], _pu);

	//ドキュメント画面のタイトル
	_pu.title = function() {
		return p.pr.title(p.co.sid['doc_main']);
	};
  
	_co.exceptEdit = [
			  'applicant',
			  'created',
			  'creator',
			  'modified',
			  'modifier',
			  'fdpardocid'
	];
	_co.exceptRead = [
			  'fdpardocid'		   //元文書ID
	];	
  
	//対象リソース
	_pu.targetRes = function() {
		return p.pr.targetRes(p.co.sid[subKey]);
	};
  
	//仮想リソース
	_pu.createTempRes = function () {
		var docResId = z350_COMMON.getParamValue('docResId');
		var src = docResId ? util.getResource(docResId, ariel.global.getCurrentUserToken()) : null;
		return $common.createTempRes(
			p.co.sid[subKey],
			function(ret) {
				if (src && src.getType().startsWith(p.co.sid['doc_main'])) {
					$common.copyToDest(src, ret, {
						except : _co.exceptRead
					});
				}  
			}
		);
	};	
//---------------アクション---------------
  
	//作成アクション読込前処理
	_pu.beforeCreate = function(res) {
		res = res || resource;
		var source = z350_COMMON.getParamValue('sourceResourceId');
		var docRes = util.getResource(source, p.co.rootToken);
		if(docRes) {
			res.getField('fdpardocid').setValue(source);
			$common.copyToDest(docRes, res, {
				except : _co.exceptEdit
			});
		}
		res.saveAsTemporary();
	};
  
	//編集アクション永続化前処理
	_pu.beforeEditWrite = function(res) {
		res = res || resource;
		var source = res.getFieldValue('fdpardocid');
		//元文書リソース
		var docRes = util.getMutableResource(source, p.co.rootToken);
		$common.copyFromSource(res, docRes, {
				except : _co.exceptEdit,
				copyAtt : false
		});
		docRes.save(p.co.saveOption);
		p.pu.sub('doc_main').setAcl(docRes);
		p.pr.messageClear();
		return false;
	};
  
	Object.freeze(_co);
	Object.freeze(_pr);
	return Object.freeze(_pu);

});