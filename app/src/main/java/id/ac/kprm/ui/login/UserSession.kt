package id.ac.kprm.ui.login

import android.content.Context
import android.content.SharedPreferences

class UserSession(context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)
    private val editor: SharedPreferences.Editor = pref.edit()

    fun checkIsLogin(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }

    fun checkIsVote(): Boolean {
        return pref.getBoolean(IS_VOTE, false)
    }

    fun putVoteStatus(isVote: Boolean) {
        editor.putBoolean(IS_VOTE, isVote)
        editor.apply()
    }

    fun login(nim: String, token: String?, id: Int?, isVote: Boolean) {
        editor.putString(KEY_NIM, nim)
        editor.putString(KEY_TOKEN, token)
        editor.putInt(KEY_ID, id!!)
        editor.putBoolean(IS_LOGIN, true)
        editor.putBoolean(IS_VOTE, isVote)
        editor.apply()
    }

    fun logout() {
        editor.clear()
        editor.apply()
    }

    companion object {
        const val PREF_NAME = "pref_name"
        const val IS_LOGIN = "is_login"
        const val KEY_NIM = "key_nim"
        const val KEY_TOKEN = "key_token"
        const val KEY_ID = "key_id"
        const val IS_VOTE = "is_vote"
    }
}