import string
import random
import pyperclip

a = ""
a = a + string.ascii_letters  # abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
a = a + string.digits  # 0123456789
a = a + string.punctuation  # !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~

password = "".join(random.sample(a, 15))
print(password)
print("Copied to clipboard")
pyperclip.copy(password)
