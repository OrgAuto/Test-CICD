import os
from env.properties import *

print(val)
commit_id = get_commit_hash()
delta_changes = get_delta_files(commit_id)

print(delta_changes)
