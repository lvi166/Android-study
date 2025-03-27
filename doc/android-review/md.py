import re

def process_line(line):
    # 检查行是否以数字+句号开头，如 "4."
    if re.match(r'^\d+\.', line):
        # 使用 re.sub 将前面的数字+句号替换为 "* ##"
        new_line = re.sub(r'^\d+\.', '* ## ', line)
    else:
        # 如果没有匹配，则在行首添加 "* ##"
        new_line = '* ## ' + line
    return new_line

def process_file(input_file, output_file):
    with open(input_file, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    with open(output_file, 'w', encoding='utf-8', newline='') as f:
        for line in lines:
            processed_line = process_line(line)
            f.write(processed_line + '\n\n')  # 写入处理后的行和一个额外的空行

if __name__ == '__main__':
    import sys
    if len(sys.argv) != 3:
        print("用法: python script.py 输入文件.md 输出文件.md")
    else:
        input_file = sys.argv[1]
        output_file = sys.argv[2]
        process_file(input_file, output_file)
        print(f"处理完成，输出保存到 {output_file}")
