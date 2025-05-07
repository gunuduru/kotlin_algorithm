package main.kotlin.programmers

import main.kotlin.programmers.Programmers_다단계칫솔판매.Companion.solution

class Programmers_다단계칫솔판매 {
    companion object {
        /**
        위로 10% 떼줌 -> node.next가 부모를 가져야겠다 -> 10%떼서 부모에 넣고 넣고 넣고 ...
        enroll에 민호는 없음
        referral = "-" 인 애 -> 민호 직속부하 -> node.next가 없더라도 민호한테 10% 떼줘야함
        칫솔 1개는 100원으로 정해져있음
        amount[i] = seller[i]의 판매량

         */
        fun solution(
            enroll: Array<String>,
            referral: Array<String>,
            seller: Array<String>,
            amount: IntArray
        ): IntArray {
            val enrollSize = enroll.size

            val parent = IntArray(enrollSize) // parent[i] = i 조직원의 부모의 idx
            // enroll의 index찾을땐 enroll.indexOf("asd") 하면 오래걸릴거같으니 맵을 만들자.

            val nameToIdx: Map<String, Int> = enroll.mapIndexed { idx, name ->
                name to idx
            }.toMap() // nameToIdx["asd"] = idx

            // 부모 연결
            referral.forEachIndexed { idx, parentName ->
                if (parentName == "-") parent[idx] = -1 // 부모 음슴
                else parent[idx] = nameToIdx[parentName]!!
            }

            val result = IntArray(enrollSize)

            for (i in seller.indices) {
                var sellerIdx = nameToIdx[seller[i]]!!
                var curAmount = amount[i] * 100 // 처음 판매가격
                while (sellerIdx != -1) { // 최상단 부모의 부모한테 갈때까지
                    val parentAmount = curAmount / 10 // 부모한테 가는 10% 먼저 계산
                    result[sellerIdx] = result[sellerIdx] + curAmount - parentAmount // 셀러의 결과값에는 부모의 몫을 뺀 나머지 판매금액 누적

                    curAmount = parentAmount // 현재 부모가 다음 셀러가 될 것이므로, 부모 몫을 curAmount에 할당
                    sellerIdx = parent[sellerIdx] // 현재 셀러의 부모를 다음 셀러로 지정
                }
            }

            return result
        }
    }
}

fun main() {
    val enroll = arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young")
    val referral = arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward")
    val seller = arrayOf("young", "john", "tod", "emily", "mary")
    val amount = intArrayOf(12, 4, 2, 5, 10)

    println(solution(enroll, referral, seller, amount).joinToString(" "))
}