package code.curriculum;

import java.util.LinkedList;
import java.util.List;

public class Curriculum {
    public static final Major TOAN_TIN = null;

    public Curriculum() {
        // TOAN_TIN
        // Create subjects
        Subject PHI1006 = new Subject("Triết học Mác - Lênin", "PHI1006", 3);
        Subject PEC1008 = new Subject("Kinh tế chính trị Mác - Lênin", "PEC1008", 2);
        Subject PHI1002 = new Subject("Chủ nghĩa xã hội khoa học", "PHI1002", 2);
        Subject HIS1001 = new Subject("Lịch sử Đảng Cộng sản Việt Nam", "HIS1001", 2);
        Subject POL1001 = new Subject("Tư tưởng Hồ Chí Minh", "POL1001", 2);
        Subject FLF1107 = new Subject("Tiếng Anh B1", "FLF1107", 5);
        Subject FLF1307 = new Subject("Tiếng Pháp B1", "FLF1307", 5);
        Subject FLF1407 = new Subject("Tiếng Trung B1", "FLF1407", 5);

        Subject INM1000 = new Subject("Tin học cơ sở", "INM1000", 2);

        Subject HIS1050 = new Subject("Cơ sở văn hóa Việt Nam", "HIS1050", 3);
        Subject GEO1050 = new Subject("Khoa học trái đất và sự sống", "GEO1050", 3);
        Subject THL1057 = new Subject("Nhà nước và pháp luật đại cương", "THL1057", 2);
        Subject MAT1060 = new Subject("Nhập môn phân tích dữ liệu", "MAT1060", 2);
        Subject PHY1070 = new Subject("Nhập môn Internet kết nối vạn vật", "PHY1070", 2);
        Subject PHY1020 = new Subject("Nhập môn Robotics", "PHY1020", 2);

        Subject PHY1100 = new Subject("Cơ - Nhiệt", "PHY1100", 3);
        Subject PHY1103 = new Subject("Điện - Quang", "PHY1103", 3);

        Subject MAT2300 = new Subject("Đại số tuyến tính 1", "MAT2300", 4);
        Subject MAT2301 = new Subject("Đại số tuyến tính 2", "MAT2301", 4);
        Subject MAT2302 = new Subject("Giải tích 1", "MAT2302", 5);
        Subject MAT2303 = new Subject("Giải tích 2", "MAT2303", 5);
        Subject MAT2304 = new Subject("Giải tích 3", "MAT2304", 4);
        Subject MAT2314 = new Subject("Phương trình vi phân", "MAT2314", 4);
        Subject MAT3409 = new Subject("Giải tích hàm ứng dụng", "MAT3409", 3);
        Subject MAT2404 = new Subject("Giải tích số", "MAT2404", 4);
        Subject MAT2405 = new Subject("Xác suất", "MAT2405", 3);
        Subject MAT2406 = new Subject("Thống kê ứng dụng", "MAT2406", 4);
        Subject MAT2407 = new Subject("Tối ưu hóa", "MAT2407", 3);
        Subject MAT2315 = new Subject("Phương pháp nghiên cứu khoa học", "MAT2315", 3);

        Subject MAT2316 = new Subject("Lập trình C++", "MAT2316", 3);
        Subject MAT2317 = new Subject("Lập trình Java", "MAT2317", 3);
        Subject MAT2318 = new Subject("Lập trình Python", "MAT2318", 3);
        Subject MAT2319 = new Subject("Lập trình Julia", "MAT2319", 3);

        Subject MAT3500 = new Subject("Toán rời rạc", "MAT3500", 4);
        Subject MAT3365 = new Subject("Phương trình đạo hàm riêng", "MAT3365", 3);
        Subject MAT3372 = new Subject("Các thành phần phần mềm", "MAT3372", 3);
        Subject MAT3366 = new Subject("Hệ thống máy tính", "MAT3366", 3);
        Subject MAT3514 = new Subject("Cấu trúc dữ liệu và thuật toán", "MAT3514", 4);
        Subject MAT3504 = new Subject("Thiết kế và đánh giá thuật toán", "MAT3504", 3);
        Subject MAT3507 = new Subject("Cơ sở dữ liệu", "MAT3507", 4);
        Subject MAT3452 = new Subject("Phân tích thống kê nhiều chiều", "MAT3452", 3);
        Subject MAT3525 = new Subject("Thực hành tính toán", "MAT3525", 2);
        Subject MAT3359 = new Subject("Thực hành chuyên ngành", "MAT3359", 3);
        Subject MAT3533 = new Subject("Học máy", "MAT3533", 3);

        Subject MAT3367 = new Subject("Đại số ứng dụng", "MAT3367", 3);
        Subject MAT3545 = new Subject("Lý thuyết tính toán", "MAT3545", 3);
        Subject MAT3539 = new Subject("Mật mã và an toàn dữ liệu", "MAT3539", 3);
        Subject MAT3323 = new Subject("Tối ưu rời rạc", "MAT3323", 3);
        Subject MAT3509 = new Subject("Ngôn ngữ hình thức và Ôtômat", "MAT3509", 3);
        Subject MAT3456 = new Subject("Logic ứng dụng", "MAT3456", 3);
        Subject MAT3531 = new Subject("Tính toán phân tán", "MAT3531", 3);
        Subject MAT3508 = new Subject("Nhập môn trí tuệ nhân tạo", "MAT3508", 3);
        Subject MAT3335 = new Subject("Đại số máy tính", "MAT3335", 3);
        Subject MAT3368 = new Subject("Thuật toán ngẫu nhiên", "PHI1006", 3);

        Subject MAT3327 = new Subject("Điều khiển tối ưu", "MAT3327", 3);
        Subject MAT3565 = new Subject("Nhập môn khai phá các tập dữ liệu lớn", "MAT3565", 3);
        Subject MAT3532 = new Subject("Tính toán song song", "MAT3532", 3);
        Subject MAT3561 = new Subject("Xử lý ngôn ngữ tự nhiên và ứng dụng", "MAT3561", 3);
        Subject MAT3562 = new Subject("Thị giác máy tính", "MAT3562", 3);
        Subject MAT3333 = new Subject("Các mô hình toán ứng dụng 1", "MAT3333", 3);
        Subject MAT3334 = new Subject("Các mô hình toán ứng dụng 2", "MAT3334", 3);
        Subject MAT3369 = new Subject("Giải tích số nâng cao", "MAT3369", 3);
        Subject MAT3370 = new Subject("Thống kê Bayes", "MAT3370", 3);

        Subject MAT4082 = new Subject("Khóa luận tốt nghiệp", "MAT4082", 7);

        Subject MAT4072 = new Subject("Một số vấn đề chọn lọc trong tính toán khoa học", "MAT4072", 4);
        Subject MAT3371 = new Subject("Xây dựng phần mềm", "MAT3371", 3);

        // Create connection between subjects
        PEC1008.addParentSubject(PHI1006);
        THL1057.addParentSubject(PHI1006);
        PHY1100.addParentSubject(MAT2302);
        PHY1103.addParentSubject(MAT2302);
        MAT2301.addParentSubject(MAT2300);
        MAT2303.addParentSubject(MAT2302);
        MAT2304.addParentSubject(MAT2303);
        MAT2314.addParentSubject(MAT2301);
        MAT2314.addParentSubject(MAT2303);
        MAT3409.addParentSubject(MAT2301);
        MAT3409.addParentSubject(MAT2304);
        MAT2404.addParentSubject(MAT2314);
        MAT2404.addParentSubject(MAT2316);
        MAT2404.addParentSubject(MAT2317);
        MAT2404.addParentSubject(MAT2318);
        MAT2404.addParentSubject(MAT2319);
        MAT2405.addParentSubject(MAT2303);
        MAT2405.addParentSubject(MAT2301);
        MAT2406.addParentSubject(MAT2405);
        MAT2407.addParentSubject(MAT2301);
        MAT2407.addParentSubject(MAT2302);
        MAT2316.addParentSubject(INM1000);
        MAT2317.addParentSubject(INM1000);
        MAT2318.addParentSubject(INM1000);
        MAT2319.addParentSubject(INM1000);
        MAT3500.addParentSubject(MAT2302);
        MAT3500.addParentSubject(MAT2300);
        MAT3365.addParentSubject(MAT2314);
        MAT3372.addParentSubject(MAT2316);
        MAT3372.addParentSubject(MAT2317);
        MAT3372.addParentSubject(MAT2318);
        MAT3372.addParentSubject(MAT2319);
        MAT3366.addParentSubject(MAT2316);
        MAT3366.addParentSubject(MAT2317);
        MAT3366.addParentSubject(MAT2318);
        MAT3366.addParentSubject(MAT2319);
        MAT3514.addParentSubject(MAT2316);
        MAT3514.addParentSubject(MAT2317);
        MAT3514.addParentSubject(MAT2318);
        MAT3514.addParentSubject(MAT2319);
        MAT3504.addParentSubject(MAT2301);
        MAT3504.addParentSubject(MAT2303);
        MAT3504.addParentSubject(MAT3500);
        MAT3504.addParentSubject(MAT3514);
        MAT3507.addParentSubject(MAT2316);
        MAT3507.addParentSubject(MAT2317);
        MAT3507.addParentSubject(MAT2318);
        MAT3507.addParentSubject(MAT2319);
        MAT3507.addParentSubject(MAT3500);
        MAT3452.addParentSubject(MAT2406);
        MAT3525.addParentSubject(MAT2404);
        MAT3533.addParentSubject(MAT2406);
        MAT3533.addParentSubject(MAT2404);
        MAT3533.addParentSubject(MAT3514);
        MAT3545.addParentSubject(MAT3500);
        MAT3545.addParentSubject(MAT3504);
        MAT3539.addParentSubject(MAT2316);
        MAT3539.addParentSubject(MAT2317);
        MAT3539.addParentSubject(MAT2318);
        MAT3539.addParentSubject(MAT2319);
        MAT3323.addParentSubject(MAT2407);
        MAT3323.addParentSubject(MAT3500);
        MAT3509.addParentSubject(MAT2316);
        MAT3509.addParentSubject(MAT2317);
        MAT3509.addParentSubject(MAT2318);
        MAT3509.addParentSubject(MAT2319);
        MAT3509.addParentSubject(MAT3500);
        MAT3456.addParentSubject(MAT2316);
        MAT3456.addParentSubject(MAT2317);
        MAT3456.addParentSubject(MAT2318);
        MAT3456.addParentSubject(MAT2319);
        MAT3456.addParentSubject(MAT3500);
        MAT3531.addParentSubject(MAT3366);
        MAT3531.addParentSubject(MAT3372);
        MAT3508.addParentSubject(MAT3507);
        MAT3508.addParentSubject(MAT3500);
        MAT3335.addParentSubject(MAT2316);
        MAT3335.addParentSubject(MAT2317);
        MAT3335.addParentSubject(MAT2318);
        MAT3335.addParentSubject(MAT2319);
        MAT3335.addParentSubject(MAT2301);
        MAT3368.addParentSubject(MAT2405);
        MAT3327.addParentSubject(MAT2304);
        // MAT3327.addParentSubject(MAT2307);
        MAT3327.addParentSubject(MAT2404);
        MAT3565.addParentSubject(MAT3514);
        MAT3565.addParentSubject(MAT2405);
        MAT3565.addParentSubject(MAT3507);
        MAT3532.addParentSubject(MAT3504);
        MAT3532.addParentSubject(MAT3366);
        MAT3561.addParentSubject(MAT3508);
        MAT3561.addParentSubject(MAT3509);
        MAT3562.addParentSubject(MAT2303);
        MAT3562.addParentSubject(MAT2301);
        MAT3562.addParentSubject(MAT2316);
        MAT3562.addParentSubject(MAT2317);
        MAT3562.addParentSubject(MAT2318);
        MAT3562.addParentSubject(MAT2319);
        MAT3333.addParentSubject(MAT2304);
        MAT3333.addParentSubject(MAT2405);
        MAT3334.addParentSubject(MAT2314);
        MAT3334.addParentSubject(MAT2406);
        MAT3323.addParentSubject(MAT2407);
        MAT3323.addParentSubject(MAT3500);
        MAT3369.addParentSubject(MAT2404);
        MAT3370.addParentSubject(MAT2405);
        MAT4072.addParentSubject(MAT2404);
        MAT4072.addParentSubject(MAT2407);
        MAT3371.addParentSubject(MAT3372);

    }
}
