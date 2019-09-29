//检查是否为正数
function  isUnsignedNumeric(strNumber)  {  var  newPar=/^\d+(\.\d+)?$/
return newPar.test(strNumber);  }
//检查是否为整数
function  isInteger(strInteger)  {
           var  newPar=/^(-  |\+)?\d+$/  
           return newPar.test(strInteger);  }
function testInt(str)
{
if(isInteger(str)&&isUnsignedNumeric(str))
return true;
else return false;
}