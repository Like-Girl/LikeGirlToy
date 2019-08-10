package cn.likegirl.lintcode.树;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1413. 树
 * 给出两个list x，y，代表x[i]与y[i]之间有一条边，整个边集构成一棵树，1为根，现在有个list a,b，表示询问节点a[i]与b[i]是什么关系，如果a[i]与b[i]是兄弟，即有同一个父节点，输出1，如果a[i]与b[i]是父子关系，输出2，否则输出0。
 * <p>
 * 样例
 * 给出 x = [1,1], y = [2,3], a =[1,2], b = [2,3], 返回[2,1]。
 * <p>
 * 解释：
 * 1与2是父子关系，2与3是兄弟关系，它们的共同父节点为1。
 * 给出 x = [1,1,2], y = [2,3,4], a = [1,2,1], b = [2,3,4], 返回[2,1,0]。
 * <p>
 * 解释：
 * 1与2是父子关系，2与3是兄弟关系，它们的共同父节点为1，1与4不是兄弟关系也不是父子关系。
 * 注意事项
 * 节点数不大于100000。
 * 所以的数均为不大于100000的正整数
 */
public class Solution {
    /**
     * @param x: The x
     * @param y: The y
     * @param a: The a
     * @param b: The b
     * @return: The Answer
     */
    public static int[] tree(int[] x, int[] y, int[] a, int[] b) {
        // Write your code here
        int[] result = new int[a.length];
        List<List<Integer>> nodes = new ArrayList<>();
        int[] nodeParent = new int[100001];
        List<List<Integer>> nodeChildren = new ArrayList<>();
        for (int i = 0; i <= 100000; i++) {
            nodes.add(new ArrayList<>());
            nodeChildren.add(new ArrayList<>());
        }
        for (int i = 0; i < x.length; i++) {
            nodes.get(x[i]).add(y[i]);
            nodes.get(y[i]).add(x[i]);
        }
        dfs(1, 0, nodes, nodeChildren, nodeParent);
        for (int i = 0; i < a.length; i++) {
            if(nodeParent[a[i]] == nodeParent[b[i]]){
                result[i] = 1;
            } else if(nodeChildren.get(a[i]).contains(b[i]) || nodeChildren.get(b[i]).contains(a[i])){
                result[i] = 2;
            }
        }
        return result;
    }

    public static void dfs(int currentNode, int parentNode, List<List<Integer>> nodes, List<List<Integer>> nodeChildren, int[] nodeParent) {
        nodeParent[currentNode] = parentNode;
        for (int i = 0; i < nodes.get(currentNode).size(); i++) {
            Integer child = nodes.get(currentNode).get(i);
            if (child != parentNode) {
                nodeChildren.get(currentNode).add(child);
                dfs(child, currentNode, nodes, nodeChildren, nodeParent);
            }
        }
    }

    public static void main(String[] args) {
        int[] x = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 805, 806, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840, 841, 842, 843, 844, 845, 846, 847, 848, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859, 860, 861, 862, 863, 864, 865, 866, 867, 868, 869, 870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 896, 897, 898, 899, 900, 901, 902, 903, 904, 905, 906, 907, 908, 909, 910, 911, 912, 913, 914, 915, 916, 917, 918, 919, 920, 921, 922, 923, 924, 925, 926, 927, 928, 929, 930, 931, 932, 933, 934, 935, 936, 937, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 982, 983, 984, 985, 986, 987, 988, 989, 990, 991, 992, 993, 994, 995, 996, 997, 998, 999, 1000};
        int[] y = {1, 2, 3, 2, 1, 5, 3, 7, 3, 8, 4, 12, 1, 1, 3, 1, 12, 10, 6, 13, 20, 14, 20, 6, 13, 5, 9, 6, 2, 14, 17, 9, 18, 9, 17, 34, 14, 23, 22, 20, 15, 20, 5, 18, 34, 22, 25, 16, 36, 35, 28, 27, 9, 25, 19, 50, 36, 22, 15, 41, 16, 5, 43, 44, 3, 50, 8, 37, 20, 45, 6, 31, 21, 46, 9, 6, 24, 26, 43, 48, 5, 2, 16, 1, 40, 10, 23, 28, 16, 12, 24, 11, 1, 16, 46, 28, 48, 37, 47, 15, 49, 43, 13, 3, 16, 39, 37, 50, 40, 19, 36, 10, 9, 48, 8, 12, 39, 39, 24, 3, 40, 40, 2, 34, 24, 16, 23, 47, 45, 37, 31, 31, 28, 45, 24, 5, 2, 14, 15, 32, 8, 40, 46, 45, 32, 24, 27, 6, 48, 22, 5, 13, 16, 32, 10, 1, 15, 20, 36, 25, 47, 13, 2, 38, 3, 30, 21, 24, 43, 24, 37, 49, 17, 43, 37, 42, 28, 35, 46, 45, 7, 46, 19, 15, 41, 37, 7, 46, 10, 35, 24, 22, 20, 32, 27, 21, 12, 5, 21, 29, 20, 16, 24, 25, 11, 6, 39, 11, 46, 13, 16, 22, 26, 33, 4, 41, 15, 48, 27, 38, 10, 42, 4, 9, 38, 37, 21, 31, 22, 43, 47, 24, 21, 42, 8, 21, 22, 10, 28, 41, 46, 27, 46, 10, 20, 48, 21, 44, 10, 44, 19, 9, 24, 28, 39, 8, 25, 29, 24, 20, 45, 17, 49, 10, 30, 13, 35, 46, 15, 50, 32, 35, 20, 25, 42, 37, 8, 48, 30, 8, 47, 16, 22, 11, 12, 23, 33, 26, 8, 49, 41, 35, 23, 35, 26, 35, 24, 11, 44, 22, 10, 6, 3, 31, 49, 9, 22, 26, 43, 10, 50, 18, 4, 9, 8, 8, 36, 42, 24, 48, 21, 11, 34, 25, 35, 29, 36, 32, 15, 47, 18, 23, 15, 32, 44, 18, 30, 22, 30, 49, 48, 7, 46, 2, 41, 44, 10, 30, 45, 32, 24, 42, 12, 2, 48, 4, 1, 8, 18, 1, 32, 19, 37, 22, 21, 27, 44, 28, 35, 15, 24, 44, 36, 32, 10, 10, 11, 15, 1, 24, 29, 24, 23, 12, 4, 20, 36, 35, 6, 2, 17, 21, 11, 16, 23, 4, 9, 29, 48, 47, 50, 12, 37, 15, 18, 20, 29, 38, 30, 21, 19, 35, 22, 27, 9, 22, 37, 35, 48, 12, 8, 34, 45, 5, 20, 17, 31, 41, 44, 47, 46, 7, 36, 28, 18, 32, 43, 12, 18, 32, 39, 41, 49, 41, 3, 20, 30, 8, 36, 4, 9, 13, 49, 15, 5, 13, 2, 10, 21, 50, 25, 49, 44, 40, 39, 35, 5, 13, 50, 10, 5, 34, 28, 22, 16, 19, 34, 10, 8, 16, 1, 50, 10, 15, 8, 49, 13, 40, 17, 24, 19, 33, 13, 45, 7, 29, 23, 30, 36, 42, 43, 33, 45, 40, 27, 37, 10, 9, 29, 11, 49, 28, 14, 11, 31, 8, 19, 28, 48, 12, 42, 41, 11, 40, 31, 2, 42, 36, 6, 33, 25, 19, 50, 49, 21, 25, 15, 17, 13, 21, 39, 8, 36, 23, 28, 37, 2, 33, 17, 21, 31, 38, 47, 8, 44, 19, 22, 43, 5, 22, 9, 23, 13, 11, 14, 28, 43, 29, 14, 33, 22, 45, 37, 28, 44, 45, 17, 17, 47, 32, 42, 36, 47, 42, 16, 5, 45, 2, 1, 16, 11, 2, 23, 33, 20, 12, 37, 15, 1, 28, 42, 46, 42, 1, 15, 22, 44, 6, 4, 49, 48, 42, 18, 6, 22, 29, 22, 39, 16, 19, 6, 7, 21, 16, 28, 23, 12, 21, 1, 29, 30, 8, 6, 11, 26, 29, 31, 33, 20, 32, 5, 4, 19, 35, 24, 2, 30, 22, 9, 25, 23, 17, 37, 47, 5, 8, 18, 26, 16, 17, 47, 14, 25, 9, 20, 2, 16, 50, 3, 10, 32, 49, 18, 25, 50, 13, 26, 22, 41, 42, 21, 19, 12, 38, 22, 6, 32, 36, 31, 14, 35, 10, 49, 3, 27, 17, 44, 20, 9, 48, 29, 8, 21, 29, 44, 49, 25, 30, 24, 38, 49, 42, 23, 31, 34, 43, 50, 25, 13, 8, 32, 20, 19, 44, 50, 2, 37, 36, 31, 6, 34, 8, 30, 42, 2, 48, 5, 35, 10, 2, 23, 8, 46, 24, 1, 50, 41, 27, 22, 23, 4, 11, 33, 14, 47, 12, 34, 13, 19, 16, 32, 41, 43, 49, 13, 33, 22, 29, 27, 32, 47, 48, 15, 36, 33, 23, 27, 2, 9, 30, 5, 7, 24, 19, 6, 7, 6, 37, 33, 5, 13, 44, 23, 14, 9, 35, 22, 9, 37, 6, 21, 33, 18, 32, 8, 49, 43, 39, 8, 36, 29, 26, 32, 26, 16, 19, 34, 4, 30, 11, 2, 42, 8, 50, 44, 22, 2, 32, 35, 5, 15, 34, 18, 18, 9, 19, 19, 45, 15, 4, 21, 45, 18, 5, 30, 3, 40, 29, 48, 46, 31, 39, 15, 30, 17, 50, 28, 34, 2, 37, 3, 48, 20, 50, 3, 8, 9, 25, 48, 15, 5, 31, 50, 44, 36, 5, 6, 35, 46, 36, 36, 18, 18, 43, 16, 43, 48, 42, 21, 28, 40, 13, 46, 21, 8, 38, 30, 49, 9, 43, 46, 23, 27, 5, 11, 38, 43, 13, 23, 8, 33, 24, 50, 45, 22, 43, 50, 34, 40, 38, 27, 37, 16, 6, 36, 31, 1, 32, 37, 13, 4, 37, 23, 25, 40, 1, 24, 26, 24, 32, 48, 33, 24, 10, 3, 24, 26, 43, 35, 11, 24, 50, 18, 5, 14, 11, 14, 27, 13, 49, 22, 41, 40, 33, 19, 34, 49, 48, 13, 43, 41, 16, 31, 13, 42, 8, 21, 47, 3, 27, 23, 41, 44, 8, 1, 48, 28, 18, 44, 32, 50, 16, 29, 4, 5, 42, 15, 19, 5};
        int[] a = {174, 818, 732, 839, 999, 407, 263, 580, 453, 487, 273, 640, 603, 724, 870, 206, 921, 521, 128, 844, 131, 759, 918, 909, 545, 323, 280, 888, 869, 117, 60, 463, 432, 91, 172, 585, 949, 980, 422, 33, 707, 990, 731, 837, 760, 424, 384, 799, 297, 96, 376, 111, 731, 991, 224, 63, 565, 184, 666, 622, 204, 541, 899, 30, 406, 362, 541, 342, 287, 663, 221, 752, 574, 858, 805, 484, 193, 155, 107, 843, 13, 966, 841, 141, 559, 733, 523, 260, 254, 988, 84, 838, 87, 979, 730, 639, 208, 783, 741, 476, 557, 889, 228, 386, 262, 109, 192, 509, 310, 980, 470, 617, 141, 939, 849, 725, 887, 378, 342, 338, 780, 710, 727, 192, 350, 687, 177, 458, 566, 890, 294, 205, 455, 472, 395, 733, 361, 606, 43, 760, 599, 579, 754, 481, 602, 277, 114, 575, 300, 469, 826, 59, 389, 870, 402, 725, 210, 1, 712, 343, 665, 836, 103, 664, 758, 150, 291, 211, 849, 597, 590, 470, 469, 481, 621, 180, 235, 790, 731, 715, 533, 862, 198, 494, 787, 676, 301, 556, 730, 410, 192, 231, 696, 156, 688, 606, 161, 971, 866, 851, 85, 483, 449, 950, 175, 365, 121, 377, 728, 205, 413, 89, 474, 101, 369, 635, 989, 185, 697, 725, 358, 17, 88, 673, 843, 2, 722, 630, 440, 901, 459, 70, 45, 974, 389, 896, 893, 527, 685, 816, 385, 866, 486, 258, 91, 43, 102, 323, 396, 836, 316, 120, 462, 78, 256, 141, 66, 910, 723, 720, 59, 758, 251, 667, 553, 240, 488, 693, 112, 647, 826, 625, 662, 502, 432, 629, 505, 998, 538, 549, 558, 685, 432, 782, 723, 697, 169, 187, 14, 33, 597, 12, 27, 509, 579, 290, 586, 993, 284, 500, 239, 253, 742, 316, 96, 688, 347, 763, 450, 56, 671, 385, 877, 940, 237, 830, 971, 827, 218, 748, 950, 906, 96, 490, 39, 202, 860, 739, 503, 863, 393, 933, 297, 258, 715, 786, 910, 152, 173, 602, 48, 529, 644, 591, 136, 890, 305, 72, 425, 875, 339, 911, 218, 681, 890, 755, 639, 341, 965, 662, 280, 868, 277, 688, 161, 493, 408, 10, 408, 238, 776, 940, 267, 271, 985, 8, 503, 973, 62, 628, 914, 25, 156, 612, 618, 127, 269, 326, 755, 207, 311, 860, 742, 871, 90, 613, 388, 870, 102, 885, 47, 907, 330, 720, 993, 60, 302, 91, 763, 248, 315, 771, 888, 685, 909, 171, 495, 162, 368, 803, 700, 767, 232, 748, 631, 120, 831, 405, 316, 918, 995, 921, 362, 501, 470, 966, 196, 287, 124, 216, 547, 688, 488, 924, 177, 40, 687, 525, 112, 324, 707, 938, 865, 605, 682, 358, 541, 479, 581, 911, 272, 316, 601, 16, 41, 519, 369, 391, 227, 961, 826, 607, 943, 815, 389, 591, 999, 497, 729, 529, 581, 764, 959, 100, 98, 922, 460, 107, 679, 965, 874, 827, 360, 360, 351, 145, 692, 766, 661, 18, 358, 240, 936, 105, 815, 67, 211, 428, 418, 240, 891, 583, 934, 251, 875, 272, 792, 532, 286, 25, 174, 209, 560, 770, 605, 730, 526, 204, 467, 971, 521, 559, 942, 134, 62, 933, 948, 38, 529, 940, 416, 677, 646, 776, 284, 935, 895, 54, 489, 405, 497, 298, 465, 691, 406, 899, 181, 954, 324, 706, 207, 479, 45, 38, 714, 266, 501, 699, 44, 632, 231, 782, 59, 761, 849, 810, 894, 122, 3, 967, 655, 356, 508, 190, 911, 44, 260, 598, 810, 481, 491, 545, 599, 249, 713, 168, 258, 412, 628, 395, 586, 671, 662, 762, 794, 595, 648, 189, 32, 823, 551, 782, 516, 77, 304, 484, 793, 361, 398, 468, 883, 144, 687, 126, 380, 123, 823, 134, 673, 627, 325, 243, 403, 775, 204, 90, 647, 7, 619, 92, 798, 365, 534, 908, 710, 583, 563, 154, 231, 423, 982, 971, 312, 27, 606, 390, 191, 213, 453, 952, 4, 251, 51, 178, 87, 744, 524, 534, 541, 737, 854, 333, 503, 721, 932, 267, 435, 630, 478, 281, 977, 178, 510, 979, 663, 603, 996, 80, 878, 339, 979, 557, 619, 417, 649, 49, 415, 254, 507, 586, 814, 552, 16, 335, 428, 515, 417, 140, 675, 235, 727, 538, 951, 499, 669, 180, 866, 137, 481, 810, 72, 87, 727, 882, 693, 868, 991, 418, 569, 601, 498, 664, 891, 141, 153, 602, 931, 825, 278, 422, 263, 796, 840, 907, 759, 641, 923, 486, 1, 972, 70, 46, 861, 656, 715, 16, 482, 660, 27, 996, 279, 465, 688, 871, 507, 38, 465, 327, 990, 509, 417, 161, 182, 563, 960, 526, 859, 244, 193, 121, 994, 604, 618, 635, 646, 167, 39, 432, 930, 2, 863, 344, 729, 679, 653, 87, 782, 165, 1000, 780, 964, 646, 660, 718, 800, 935, 615, 146, 90, 796, 477, 694, 927, 146, 133, 578, 514, 586, 177, 217, 84, 484, 4, 300, 260, 882, 478, 467, 39, 792, 984, 6, 779, 494, 539, 135, 127, 395, 104, 264, 401, 640, 479, 267, 288, 203, 889, 776, 27, 461, 301, 955, 384, 708, 135, 371, 869, 154, 140, 964, 907, 164, 159, 414, 398, 917, 40, 300, 462, 832, 280, 357, 817, 193, 598, 830, 247, 460, 453, 686, 709, 388, 283, 393, 227, 462, 595, 94, 117, 275, 236, 41, 149, 394, 761, 730, 959, 83, 431, 292, 28, 205, 81, 215, 968, 602, 7, 302, 317, 413, 19, 281, 709, 963, 59, 884, 83, 887, 842, 254, 59, 820, 807, 915, 870, 487, 572, 603, 79, 898, 848, 889, 792, 315, 693, 44, 102, 99, 403, 553, 792, 727, 635, 96, 908, 282, 189, 395, 882, 745, 251, 18, 368, 981, 972, 417, 556, 533, 66, 760, 61, 650, 43, 554, 570, 881, 380, 438, 617, 300, 865, 609, 890, 449, 894, 123, 729, 57, 850, 814, 632, 179, 47, 170, 634, 799, 539, 763, 313, 771, 794, 81, 825, 473, 385, 295, 563, 239, 92, 140};
        int[] b = {355, 100, 546, 765, 420, 855, 471, 971, 539, 195, 574, 734, 309, 384, 461, 432, 211, 575, 1000, 238, 602, 430, 855, 589, 331, 536, 571, 164, 66, 602, 309, 771, 803, 143, 423, 580, 304, 637, 920, 377, 876, 558, 324, 517, 772, 799, 930, 673, 545, 364, 629, 609, 183, 211, 748, 188, 265, 437, 441, 880, 603, 712, 117, 348, 894, 727, 251, 916, 704, 120, 609, 192, 553, 205, 418, 653, 470, 700, 783, 619, 346, 879, 345, 709, 992, 189, 56, 100, 67, 895, 567, 12, 791, 123, 957, 622, 293, 165, 550, 669, 218, 705, 65, 705, 872, 162, 856, 982, 484, 198, 794, 269, 166, 319, 396, 858, 53, 652, 731, 37, 536, 358, 372, 130, 438, 372, 29, 553, 438, 80, 761, 119, 600, 175, 968, 514, 827, 532, 788, 33, 508, 21, 53, 474, 478, 805, 646, 65, 772, 345, 110, 560, 552, 891, 432, 649, 128, 914, 379, 646, 460, 783, 171, 394, 913, 162, 78, 678, 721, 571, 984, 413, 624, 282, 335, 463, 296, 767, 576, 881, 943, 554, 680, 380, 380, 405, 475, 138, 133, 517, 441, 92, 100, 669, 452, 543, 909, 914, 308, 26, 778, 920, 59, 114, 759, 707, 497, 318, 558, 327, 280, 779, 972, 25, 9, 289, 435, 500, 542, 11, 342, 869, 262, 932, 488, 279, 661, 461, 189, 600, 184, 616, 838, 298, 549, 744, 225, 178, 545, 806, 48, 452, 8, 77, 654, 814, 586, 999, 981, 857, 592, 838, 490, 507, 810, 820, 598, 7, 932, 660, 199, 235, 853, 46, 590, 113, 71, 63, 719, 655, 105, 239, 236, 727, 856, 785, 394, 127, 745, 838, 666, 770, 969, 803, 233, 118, 366, 798, 793, 396, 91, 804, 309, 451, 608, 838, 641, 621, 159, 448, 320, 16, 416, 849, 140, 886, 302, 686, 986, 558, 440, 751, 986, 689, 865, 704, 308, 238, 129, 289, 814, 838, 41, 406, 735, 264, 827, 128, 679, 927, 431, 354, 724, 759, 335, 21, 695, 283, 212, 20, 836, 546, 775, 65, 126, 677, 857, 727, 601, 787, 915, 565, 790, 646, 839, 129, 75, 75, 572, 434, 544, 285, 423, 693, 475, 227, 636, 218, 685, 349, 297, 644, 971, 683, 700, 956, 581, 183, 368, 670, 618, 838, 388, 819, 698, 210, 387, 253, 315, 131, 221, 126, 693, 569, 517, 922, 270, 148, 945, 288, 582, 973, 958, 725, 332, 410, 89, 521, 768, 269, 278, 488, 478, 542, 133, 322, 897, 308, 743, 907, 286, 600, 70, 723, 965, 803, 947, 184, 956, 634, 734, 695, 403, 353, 137, 887, 177, 550, 125, 934, 181, 617, 243, 442, 454, 203, 812, 498, 132, 868, 579, 369, 972, 499, 261, 362, 816, 89, 400, 471, 693, 950, 393, 824, 83, 75, 700, 66, 681, 847, 912, 525, 238, 523, 840, 22, 665, 509, 11, 619, 529, 896, 181, 537, 742, 690, 92, 793, 319, 685, 340, 391, 329, 439, 396, 370, 942, 874, 126, 119, 868, 589, 170, 6, 202, 969, 796, 722, 382, 229, 394, 877, 66, 554, 223, 459, 274, 727, 938, 386, 464, 230, 456, 704, 25, 61, 901, 717, 381, 103, 279, 749, 914, 752, 236, 935, 953, 485, 919, 33, 960, 49, 775, 545, 102, 385, 992, 261, 475, 473, 328, 428, 789, 444, 210, 251, 628, 465, 151, 330, 951, 960, 408, 812, 612, 393, 829, 258, 292, 518, 169, 216, 436, 276, 588, 814, 382, 549, 928, 993, 22, 713, 708, 288, 285, 504, 524, 791, 298, 378, 231, 55, 356, 567, 840, 244, 601, 255, 285, 534, 98, 132, 355, 457, 396, 740, 7, 876, 964, 871, 609, 297, 354, 423, 115, 723, 515, 123, 563, 435, 838, 994, 699, 179, 161, 622, 204, 614, 965, 435, 94, 275, 365, 244, 464, 222, 435, 407, 534, 494, 227, 788, 734, 112, 214, 658, 643, 26, 736, 610, 106, 777, 937, 578, 64, 172, 640, 947, 626, 599, 932, 67, 786, 128, 233, 787, 465, 789, 385, 90, 248, 629, 833, 889, 709, 410, 904, 317, 653, 30, 955, 489, 460, 11, 611, 441, 150, 649, 797, 320, 458, 483, 705, 50, 314, 546, 252, 715, 652, 329, 934, 646, 315, 544, 549, 694, 519, 585, 472, 353, 945, 326, 36, 376, 472, 161, 235, 633, 87, 762, 360, 917, 644, 852, 60, 762, 667, 80, 708, 602, 529, 289, 417, 546, 57, 583, 527, 876, 763, 716, 886, 797, 935, 890, 228, 736, 70, 99, 641, 281, 386, 351, 905, 371, 977, 210, 622, 194, 686, 131, 43, 106, 752, 270, 90, 414, 945, 778, 71, 954, 960, 964, 731, 138, 424, 314, 162, 720, 871, 849, 973, 969, 175, 872, 393, 857, 632, 675, 166, 27, 596, 481, 80, 570, 362, 718, 58, 955, 764, 10, 798, 64, 481, 872, 206, 942, 291, 575, 854, 560, 802, 820, 64, 594, 967, 862, 702, 239, 938, 62, 484, 450, 173, 413, 154, 353, 648, 221, 593, 191, 156, 366, 860, 142, 907, 797, 697, 950, 671, 140, 937, 161, 964, 727, 324, 239, 954, 97, 731, 227, 76, 285, 548, 124, 275, 981, 490, 597, 739, 478, 243, 621, 956, 786, 696, 688, 90, 309, 711, 28, 854, 849, 156, 13, 307, 243, 252, 409, 994, 483, 105, 436, 863, 391, 18, 606, 130, 464, 368, 344, 391, 291, 881, 723, 141, 970, 312, 968, 314, 553, 763, 457, 830, 442, 324, 183, 948, 633, 657, 802, 549, 72, 293, 901, 516, 556, 868, 766, 530, 742, 82, 938, 788, 780, 965, 775, 216, 813, 254, 504, 244, 128, 485, 301, 351, 990, 698, 905, 262, 747, 420, 890, 616, 953, 261, 868, 890, 37, 156, 22, 956, 848, 82, 35, 266, 983, 30, 747, 604, 756, 146, 968, 623, 250, 659, 705, 553, 949, 148, 98, 9, 744, 268, 65, 45, 757, 363, 667, 689, 9, 857, 13, 199, 429, 199, 748, 242, 254, 996, 686, 113, 693, 66, 414, 115, 520, 151, 196, 957, 159};
        System.out.println(Arrays.toString(tree(x, y, a, b)));
    }

}