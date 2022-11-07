package com.wahyuabid.myapplication

import com.wahyuabid.myapplication.model.MemberModel

fun MemberModel.getSprintValue(): Int {
    if (this.sex.lowercase() == "L".lowercase()) {
        if (dateBirth >= 6 && dateBirth <= 9) {
            if (tes1 > 0 && tes1 <= 5.5)
                return 5
            else if (tes1 > 5.5 && tes1 <= 6.1)
                return 4
            else if (tes1 > 6.1 && tes1 <= 6.9) {
                return 3
            } else if (tes1 > 6.9 && tes1 <= 8.6) {
                return 2
            } else {
                return 1
            }
        } else if (dateBirth >= 10 && dateBirth <= 12) {
            if (tes1 > 0 && tes1 <= 6.3)
                return 5
            else if (tes1 > 6.3 && tes1 <= 6.9)
                return 4
            else if (tes1 > 6.9 && tes1 <= 7.7) {
                return 3
            } else if (tes1 > 7.7 && tes1 <= 8.8) {
                return 2
            } else {
                return 1
            }
        } else if (dateBirth >= 16 && dateBirth <= 19) {
            if (tes1 > 0 && tes1 <= 7.2)
                return 5
            else if (tes1 > 7.2 && tes1 <= 8.3)
                return 4
            else if (tes1 > 8.3 && tes1 <= 9.6) {
                return 3
            } else if (tes1 > 9.6 && tes1 <= 11.0) {
                return 2
            } else {
                return 1
            }
        } else if (dateBirth >= 13 && dateBirth <= 15) {
            if (tes1 > 0 && tes1 <= 6.3)
                return 5
            else if (tes1 > 6.3 && tes1 <= 6.9)
                return 4
            else if (tes1 > 6.9 && tes1 <= 7.7) {
                return 3
            } else if (tes1 > 7.7 && tes1 <= 8.8) {
                return 2
            } else {
                return 1
            }
        }
    } else {
        if (dateBirth >= 6 && dateBirth <= 9) {
            if (tes1 > 0 && tes1 <= 5.8)
                return 5
            else if (tes1 > 5.8 && tes1 <= 6.6)
                return 4
            else if (tes1 > 6.6 && tes1 <= 7.8) {
                return 3
            } else if (tes1 > 7.8 && tes1 <= 9.2) {
                return 2
            } else {
                return 1
            }
        } else if (dateBirth >= 10 && dateBirth <= 12) {
            if (tes1 > 0 && tes1 <= 6.7)
                return 5
            else if (tes1 > 6.7 && tes1 <= 7.5)
                return 4
            else if (tes1 > 7.5 && tes1 <= 8.3) {
                return 3
            } else if (tes1 > 8.3 && tes1 <= 9.6) {
                return 2
            } else {
                return 1
            }
        } else if (dateBirth >= 16 && dateBirth <= 19) {
            if (tes1 > 0 && tes1 <= 8.4)
                return 5
            else if (tes1 > 8.4 && tes1 <= 9.8)
                return 4
            else if (tes1 > 9.8 && tes1 <= 11.4) {
                return 3
            } else if (tes1 > 11.4 && tes1 <= 13.4) {
                return 2
            } else {
                return 1
            }
        } else if (dateBirth >= 13 && dateBirth <= 15) {
            if (tes1 > 0 && tes1 <= 7.7)
                return 5
            else if (tes1 > 7.7 && tes1 <= 8.7)
                return 4
            else if (tes1 > 8.7 && tes1 <= 9.9) {
                return 3
            } else if (tes1 > 9.9 && tes1 <= 11.9) {
                return 2
            } else {
                return 1
            }
        }
    }
    return 0
}

fun MemberModel.getPullUpValue(): Int {
    if (this.sex.lowercase() == "L".lowercase()) {
        if (dateBirth >= 6 && dateBirth <= 9) {
            if (tes2 > 0 && tes2 <= 2)
                return 1
            else if (tes2 > 2 && tes2 <= 8)
                return 2
            else if (tes2 > 8 && tes2 <= 21) {
                return 3
            } else if (tes2 > 21 && tes2 <= 39) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth >= 10 && dateBirth <= 12) {
            if (tes2 > 0 && tes2 <= 4)
                return 1
            else if (tes2 > 4 && tes2 <= 14)
                return 2
            else if (tes2 > 14 && tes2 <= 30) {
                return 3
            } else if (tes2 > 31 && tes2 <= 51) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth >= 16 && dateBirth <= 19) {
            if (tes2 > 0 && tes2 <= 4)
                return 1
            else if (tes2 > 4 && tes2 <= 8)
                return 2
            else if (tes2 > 8 && tes2 <= 13) {
                return 3
            } else if (tes2 > 13 && tes2 <= 18.0) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth >= 13 && dateBirth <= 15) {
            if (tes2 > 0 && tes2 <= 1)
                return 1
            else if (tes2 > 1 && tes2 <= 5)
                return 2
            else if (tes2 > 5 && tes2 <= 10) {
                return 3
            } else if (tes2 > 10 && tes2 <= 15) {
                return 4
            } else {
                return 5
            }
        }
    } else {
        if (dateBirth >= 6 && dateBirth <= 9) {
            if (tes2 > 0 && tes2 <= 2)
                return 1
            else if (tes2 > 3 && tes2 <= 8)
                return 2
            else if (tes2 > 8 && tes2 <= 17) {
                return 3
            } else if (tes2 > 17 && tes2 <= 32) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth >= 10 && dateBirth <= 12) {
            if (tes2 > 0 && tes2 <= 1)
                return 1
            else if (tes2 > 1 && tes2 <= 7)
                return 2
            else if (tes2 > 7 && tes2 <= 19) {
                return 3
            } else if (tes2 > 19 && tes2 <= 39) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth >= 16 && dateBirth <= 19) {
            if (tes2 > 0 && tes2 <= 2)
                return 1
            else if (tes2 > 2 && tes2 <= 7)
                return 2
            else if (tes2 > 7 && tes2 <= 19) {
                return 3
            } else if (tes2 > 19 && tes2 <= 39) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth >= 13 && dateBirth <= 15) {
            if (tes2 > 0 && tes2 <= 2)
                return 1
            else if (tes2 > 7.7 && tes2 <= 9)
                return 2
            else if (tes2 > 8.7 && tes2 <= 21) {
                return 3
            } else if (tes2 > 9.9 && tes2 <= 40) {
                return 4
            } else {
                return 5
            }
        }
    }
    return 0
}

fun MemberModel.getSitUpValue(): Int {
    if (this.sex.lowercase() == "L".lowercase()) {
        if (dateBirth in 6.0..9.0) {
            if (tes3 > 0 && tes3 <= 1)
                return 1
            else if (tes3 > 1 && tes3 <= 6)
                return 2
            else if (tes3 > 6 && tes3 <= 12) {
                return 3
            } else if (tes3 > 12 && tes3 <= 16) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth in 10.0..12.0) {
            if (tes3 > 0 && tes3 <= 3)
                return 1
            else if (tes3 > 3 && tes3 <= 11)
                return 2
            else if (tes3 > 11 && tes3 <= 17) {
                return 3
            } else if (tes3 > 17 && tes3 <= 22) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth in 13.0..15.0) {
            if (tes3 > 0 && tes3 <= 7)
                return 1
            else if (tes3 > 7 && tes3 <= 18)
                return 2
            else if (tes3 > 18 && tes3 <= 27) {
                return 3
            } else if (tes3 > 27 && tes3 <= 37) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth in 16.0..19.0) {
            if (tes3 > 0 && tes3 <= 9)
                return 1
            else if (tes3 > 9 && tes3 <= 20)
                return 2
            else if (tes3 > 20 && tes3 <= 29) {
                return 3
            } else if (tes3 > 29 && tes3 <= 40) {
                return 4
            } else {
                return 5
            }
        }
    } else {
        if (dateBirth in 6.0..9.0) {
            if (tes3 > 0 && tes3 <= 1)
                return 1
            else if (tes3 > 1 && tes3 <= 3)
                return 2
            else if (tes3 > 3 && tes3 <= 10) {
                return 3
            } else if (tes3 > 10 && tes3 <= 214) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth in 10.0..12.0) {
            if (tes3 > 0 && tes3 <= 1)
                return 1
            else if (tes3 > 1 && tes3 <= 6)
                return 2
            else if (tes3 > 6 && tes3 <= 13) {
                return 3
            } else if (tes3 > 13 && tes3 <= 19) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth in 16.0..19.0) {
            if (tes3 > 0 && tes3 <= 2)
                return 1
            else if (tes3 > 2 && tes3 <= 9)
                return 2
            else if (tes3 > 9 && tes3 <= 19) {
                return 3
            } else if (tes3 > 19 && tes3 <= 28) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth in 13.0..15.0) {
            if (tes3 > 0 && tes3 <= 2)
                return 1
            else if (tes3 > 2 && tes3 <= 8)
                return 2
            else if (tes3 > 8 && tes3 <= 18) {
                return 3
            } else if (tes3 > 18 && tes3 <= 27) {
                return 4
            } else {
                return 5
            }
        }
    }
    return 0
}

fun MemberModel.getVerticalJumpValue(): Int {
    if (this.sex.lowercase() == "L".lowercase()) {
        if (dateBirth in 6.0..9.0) {
            if (tes4.maxOf { it } > 0 && tes4.maxOf { it } <= 12)
                return 1
            else if (tes4.maxOf { it } > 12 && tes4.maxOf { it } <= 21)
                return 2
            else if (tes4.maxOf { it } > 21 && tes4.maxOf { it } <= 29) {
                return 3
            } else if (tes4.maxOf { it } > 29 && tes4.maxOf { it } <= 37) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth in 10.0..12.0) {
            if (tes4.maxOf { it } > 0 && tes4.maxOf { it } <= 23)
                return 1
            else if (tes4.maxOf { it } > 23 && tes4.maxOf { it } <= 30)
                return 2
            else if (tes4.maxOf { it } > 30 && tes4.maxOf { it } <= 37) {
                return 3
            } else if (tes4.maxOf { it } > 37 && tes4.maxOf { it } <= 45) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth in 16.0..19.0) {
            if (tes4.maxOf { it } > 0 && tes4.maxOf { it } <= 38)
                return 1
            else if (tes4.maxOf { it } > 38 && tes4.maxOf { it } <= 49)
                return 2
            else if (tes4.maxOf { it } < 49 && tes4.maxOf { it } <= 59) {
                return 3
            } else if (tes4.maxOf { it } > 59 && tes4.maxOf { it } <= 72) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth in 13.0..15.0) {
            if (tes4.maxOf { it } > 0 && tes4.maxOf { it } <= 30)
                return 1
            else if (tes4.maxOf { it } > 30 && tes4.maxOf { it } <= 41)
                return 2
            else if (tes4.maxOf { it } > 41 && tes4.maxOf { it } <= 52) {
                return 3
            } else if (tes4.maxOf { it } > 52 && tes4.maxOf { it } <= 56) {
                return 4
            } else {
                return 5
            }
        }
    } else {
        if (dateBirth in 6.0..9.0) {
            if (tes4.maxOf { it } > 0 && tes4.maxOf { it } <= 12)
                return 1
            else if (tes4.maxOf { it } > 12 && tes4.maxOf { it } <= 21)
                return 2
            else if (tes4.maxOf { it } > 21 && tes4.maxOf { it } <= 29) {
                return 3
            } else if (tes4.maxOf { it } > 29 && tes4.maxOf { it } <= 37) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth in 10.0..12.0) {
            if (tes4.maxOf { it } > 0 && tes4.maxOf { it } <= 20)
                return 1
            else if (tes4.maxOf { it } > 20 && tes4.maxOf { it } <= 27)
                return 2
            else if (tes4.maxOf { it } > 27 && tes4.maxOf { it } <= 33) {
                return 3
            } else if (tes4.maxOf { it } > 33 && tes4.maxOf { it } <= 41) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth in 16.0..19.0) {
            if (tes4.maxOf { it } > 0 && tes4.maxOf { it } <= 22)
                return 1
            else if (tes4.maxOf { it } > 22 && tes4.maxOf { it } <= 30)
                return 2
            else if (tes4.maxOf { it } > 30 && tes4.maxOf { it } <= 38) {
                return 3
            } else if (tes4.maxOf { it } > 38 && tes4.maxOf { it } <= 49) {
                return 4
            } else {
                return 5
            }
        } else if (dateBirth in 13.0..15.0) {
            if (tes4.maxOf { it } > 0 && tes4.maxOf { it } <= 20)
                return 1
            else if (tes4.maxOf { it } > 20 && tes4.maxOf { it } <= 29)
                return 2
            else if (tes4.maxOf { it } > 29 && tes4.maxOf { it } <= 38) {
                return 3
            } else if (tes4.maxOf { it } > 38 && tes4.maxOf { it } <= 49) {
                return 4
            } else {
                return 5
            }
        }
    }
    return 0
}


fun MemberModel.getLariJarakSedangValue(): Int {
    if (this.sex.lowercase() == "L".lowercase()) {
        if (dateBirth in 6.0..9.0) {
            if (tes5 in 0.0..2.39)
                return 5
            else if (tes5 in 2.40..3.0)
                return 4
            else if (tes5 > 3 && tes5 <= 3.45) {
                return 3
            } else if (tes5 > 3.45 && tes5 <= 4.48) {
                return 2
            } else {
                return 1
            }
        } else if (dateBirth in 10.0..12.0) {
            if (tes5 > 0 && tes5 <= 2.09)
                return 5
            else if (tes5 > 2.09 && tes5 <= 2.30)
                return 4
            else if (tes5 > 2.30 && tes5 <= 2.45) {
                return 3
            } else if (tes5 > 2.45 && tes5 <= 3.44) {
                return 2
            } else {
                return 1
            }
        } else if (dateBirth in 16.0..19.0) {
            if (tes5 > 0 && tes5 <= 3.14)
                return 5
            else if (tes5 > 3.14 && tes5 <= 4.25)
                return 4
            else if (tes5 < 4.25 && tes5 <= 5.12) {
                return 3
            } else if (tes5 > 5.12 && tes5 <= 6.33) {
                return 2
            } else {
                return 1
            }
        } else if (dateBirth in 13.0..15.0) {
            if (tes5 > 0 && tes5 <= 3.04)
                return 5
            else if (tes5 > 3.04 && tes5 <= 3.53)
                return 4
            else if (tes5 > 3.53 && tes5 <= 4.46) {
                return 3
            } else if (tes5 > 4.46 && tes5 <= 6.04) {
                return 2
            } else {
                return 1
            }
        }
    } else {
        if (dateBirth in 6.0..9.0) {
            if (tes5 > 0 && tes5 <= 2.53)
                return 5
            else if (tes5 > 2.53 && tes5 <= 3.23)
                return 4
            else if (tes5 > 3.23 && tes5 <= 4.08) {
                return 3
            } else if (tes5 > 4.08 && tes5 <= 5.03) {
                return 2
            } else {
                return 1
            }
        } else if (dateBirth in 10.0..12.0) {
            if (tes5 > 0 && tes5 <= 2.32)
                return 5
            else if (tes5 > 20 && tes5 <= 2.54)
                return 4
            else if (tes5 > 27 && tes5 <= 3.28) {
                return 3
            } else if (tes5 > 33 && tes5 <= 4.22) {
                return 2
            } else {
                return 1
            }
        } else if (dateBirth in 16.0..19.0) {
            if (tes5 > 0 && tes5 <= 3.52)
                return 5
            else if (tes5 > 3.52 && tes5 <= 4.56)
                return 4
            else if (tes5 > 4.56 && tes5 <= 5.58) {
                return 3
            } else if (tes5 > 5.58 && tes5 <= 7.23) {
                return 2
            } else {
                return 1
            }
        } else if (dateBirth in 13.0..15.0) {
            if (tes5 > 0 && tes5 <= 3.08)
                return 5
            else if (tes5 > 3.08 && tes5 <= 3.55)
                return 4
            else if (tes5 > 3.55 && tes5 <= 4.58) {
                return 3
            } else if (tes5 > 4.58 && tes5 <= 6.4) {
                return 2
            } else {
                return 1
            }
        }
    }
    return 0
}

fun MemberModel.getTotal(): String {
    val total =
        getSprintValue() + getSitUpValue() + getPullUpValue() + getVerticalJumpValue() + getLariJarakSedangValue()
    when (total) {
        in 22..25 -> "Baik Sekali(BS)"
        in 18..21 -> "Baik(B)"
        in 14..17 -> "Sedang(S)"
        in 10..13 -> "Kurang(K)"
        in 5..9 -> "Kurang Sekali(KS)"
    }
    return "Nilai Tidak Terkategori"
}
