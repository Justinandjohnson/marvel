import java.math.BigInteger
import java.security.MessageDigest

object MarvelAuthUtils {
    fun generateMarvelHash(timestamp: String, publicKey: String, privateKey: String): String {
        val input = timestamp + privateKey + publicKey
        val md5Hash = MessageDigest.getInstance("MD5").digest(input.toByteArray())
        return BigInteger(1, md5Hash).toString(16).padStart(32, '0')
    }
}
