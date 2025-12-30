import re

from z3 import *

buttonSets = []
joltages = []
with open('101225', 'r') as f:
    content = f.read()
    lines = content.split("\n")

for each in lines:
    matches = re.findall(r'\{([^\{\}]*)\}', each)
    if matches:
        joltages.append([int(i) for i in matches[0].split(",")])
    # print(joltages)
    button_matches = re.findall(r'\(([^\(\)]*)\)', each)
    buttonSet = []
    if button_matches:
        for each in button_matches:
            buttonSet.append([int(i) for i in each.split(",")])
    buttonSets.append(buttonSet)
    # print(buttonSet)
ans = 0
for i in range(len(joltages)):
    buttonSet = buttonSets[i]
    joltage = joltages[i]
    length = len(buttonSet)
    solver = Optimize()
    vars = [Int(f'x{k}') for k in range(length)]
    var_dict = {f'x{k}': vars[k] for k in range(length)}
    for each in vars:
        solver.add(each >= 0)
    equations = ["" for _ in range(len(joltage))]
    minimize = ["" for _ in range(len(joltage))]
    for position, value in enumerate(joltage):
        terms = []
        for button_position, button in enumerate(buttonSet):
            if (position in button):
                terms.append(str(vars[button_position]))
        equations[position] = " + ".join(terms) + " == " + str(value)
        minimize[position] = " + ".join(terms)
        print(equations[position])
    [solver.add(eval(equation, var_dict)) for equation in equations]
    solver.minimize(Sum(vars))

    if solver.check() == sat:
        model = solver.model()
        for each in vars:
            ans += model[each].as_long()
print(ans)
