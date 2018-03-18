/**
 * 通过lb类型获取中文描述
 * @param lb
 * @returns {string}
 */
function getLbName(lb) {
    return lb=='Leaved'?'请假人员':
              lb=='Driver'?'驾驶人员':
                lb=='Personnel'?'工作人员':
                    lb=='Participant'?'参会人员':''
}
/**
 * 通过xb类型获取中文描述
 * @param xb
 * @returns {string}
 */
function getXb(xb) {
    return xb=='male'? '男':'女';
}
function getZsBz(zsBz) {
    return zsBz=='notwant'? '不住宿':'住宿';
}