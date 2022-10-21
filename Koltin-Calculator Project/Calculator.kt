fun main() {


    println("Enter your equation using +,-,/,or * |\'^\' or \'()\' not implemented yet.")
    var equation:String= readLine().toString()

    calculate(equation)

}


fun calculate(equation:String ){


    println("Original equation: $equation")

    var num_arry= equation.split('+','-','*','/').toList()
    var opp_arry= mutableListOf<Char>()

    var j:Int=0
    while(j<equation.length) {
        if (equation[j] == '*' || equation[j] == '/' || equation[j] == '-' || equation[j] == '+') {
            opp_arry.add(equation[j])
        }
        j++
    }
    println("This is the opp array $opp_arry\nThis is the num array $num_arry\n")


    // now we are going to make a  hash map for the operations, putting / or * first
    //the key will be the position, the value will be the number and the opp.
    //Since maps cannot be indexed by position, we will make an indexing list as well

    var opp_map= mutableMapOf<Int,Any?>()
    var num_map= mutableMapOf<Int,Double>()
    var index= mutableListOf<Int>()


    var k:Int=0
    for (t in opp_arry){
        if (t=='/'|| t=='*'){
            opp_map.put(k,t)
            index.add(k)
            k++
        }else{
            k++
        }

    }

    k=0
    for (t in opp_arry){
        if (t=='/'|| t=='*'){
            k++
        }else{
            opp_map.put(k,t)
            index.add(k)
            k++
        }
    }


    println("The opps map: $opp_map\nThe index of opps map: $index")

    var i:Int=0
    while (i<num_arry.size){
        num_map.put(i, num_arry[i].toDouble())

        i++

    }

    println("The num map: $num_map\n")


    //Now that we have the pieces together we will now do the operations
    i=0

    while (i<index.size){

        var curr=index[i].toInt()


        var op=opp_map.get(curr)
        var nL: Double? = num_map.get(curr)
        var nR: Double? =num_map.get(curr+1)


        if(num_map.size>2) {


            var  eq_value = nL?.toDouble()?.let { nR?.toDouble()?.let { it1 -> do_math(it, it1, op as Char?)?.toDouble() } }!!


            println("char $op  numL $nL numR $nR answer $eq_value")

            num_map.remove(curr + 1)
            num_map.remove(curr)
            if (eq_value != null) {
                num_map.put(curr, eq_value.toDouble())
            }

            println(num_map)



        }else{

            var f_index=num_map.keys.toList()
            var f_in1=num_map.get(f_index[0])

            var f_in2=num_map.get(f_index[1])


            var final_answ= f_in1?.let { f_in2?.let { it1 -> do_math(it.toDouble(), it1.toDouble(), op as Char?) } }
            println(final_answ)

        }

        i++

    }

}

fun do_math(n1: Double, n2: Double, op2:Char?): Number? {

    var answ= when(op2){
        '*'-> n2?.let { n1?.times(it) }
        '/'-> n1!! / n2!!
        '-'-> n1!! - n2!!
        '+'-> n1!! + n2!!
        else -> 1.0
    }
    return answ

}
